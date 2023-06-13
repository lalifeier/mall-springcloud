package com.github.lalifeier.mall.cloud.gateway.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lalifeier.mall.cloud.common.model.EncryptBody;
import com.github.lalifeier.mall.cloud.common.utils.EncryptBodyUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.Charsets;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;


@Slf4j
@Component
public class ResponseEncryptFilter implements GatewayFilterFactory<ResponseEncryptFilter.Config>, Ordered {

  private static final ObjectMapper objectMapper = new ObjectMapper();


  private boolean shouldEncrypt(ServerWebExchange exchange, Config config) {
    ServerHttpRequest request = exchange.getRequest();
    String path = request.getPath().toString();

    // 判断白名单和黑名单
//    if (WebFluxUtil.isPathMatch(path, config.getWhiteList())
//      || !WebFluxUtil.isPathMatch(path, config.getBlackList())) {
//      return false;
//    }

    return true;
  }

  @Override
  public GatewayFilter apply(Config config) {
    return (exchange, chain) -> {
      if (!shouldEncrypt(exchange, config)) {
        return chain.filter(exchange);
      }

      ServerHttpResponse response = exchange.getResponse();
      HttpStatus statusCode = response.getStatusCode();
      HttpHeaders headers = response.getHeaders();

      if (!Objects.equals(statusCode, HttpStatus.OK)) {
        return chain.filter(exchange);
      }

      DataBufferFactory bufferFactory = response.bufferFactory();

      ServerHttpResponseDecorator decoratedResponse = buildResponse(response, bufferFactory, config);
      decoratedResponse.setStatusCode(statusCode);
//      decoratedResponse.getHeaders().putAll(headers);
//      decoratedResponse.getHeaders().setContentType(MediaType.APPLICATION_JSON);

      return chain.filter(exchange.mutate().response(decoratedResponse).build());
    };
  }

  private ServerHttpResponseDecorator buildResponse(ServerHttpResponse response, DataBufferFactory bufferFactory, Config config) {
    return new ServerHttpResponseDecorator(response) {
      @Override
      public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
        if (!getStatusCode().equals(HttpStatus.OK) || !(body instanceof Flux)) {
          log.error("获取响应体数据 ：" + getStatusCode());
          return super.writeWith(body);
        }

        Flux<? extends DataBuffer> fluxBody = Flux.from(body);

        return super.writeWith(fluxBody.buffer().map(dataBuffers -> {
          DataBufferFactory dataBufferFactory = new DefaultDataBufferFactory();
          DataBuffer dataBuffer = dataBufferFactory.join(dataBuffers);
          byte[] content = new byte[dataBuffer.readableByteCount()];
          dataBuffer.read(content);
          DataBufferUtils.release(dataBuffer);
          String data = new String(content, Charsets.UTF_8);

          try {
            log.info("加密响应数据 开始 ：{}", data);
            EncryptBody encryptBody = EncryptBodyUtil.encrypt(data, config.getPublicKey());
            log.info("加密响应数据 完成 ：{}", encryptBody);

            byte[] encryptBodyBytes = objectMapper.writeValueAsBytes(encryptBody);
            response.getHeaders().setContentLength(encryptBodyBytes.length);
            return bufferFactory.wrap(encryptBodyBytes);
          } catch (Exception e) {
            log.error("Failed to encrypt the response body", e);
            response.getHeaders().setContentLength(0L);
            return bufferFactory.allocateBuffer();
          }
        }));
      }

      @Override
      public Mono<Void> writeAndFlushWith(Publisher<? extends Publisher<? extends DataBuffer>> body) {
        return writeWith(Flux.from(body).flatMapSequential(p -> p));
      }
    };
  }

  @Override
  public Class<Config> getConfigClass() {
    return Config.class;
  }

  @Data
  public static class Config {
    private String publicKey;
    private String privateKey;
    private List<String> whiteList;
    private List<String> blackList;
  }


  @Override
  public int getOrder() {
    return Ordered.HIGHEST_PRECEDENCE;
  }
}
