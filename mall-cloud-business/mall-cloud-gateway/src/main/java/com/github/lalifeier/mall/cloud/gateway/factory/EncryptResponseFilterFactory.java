package com.github.lalifeier.mall.cloud.gateway.factory;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lalifeier.mall.cloud.common.model.EncryptBody;
import com.github.lalifeier.mall.cloud.common.utils.EncryptBodyUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;


@Slf4j
@Component
public class EncryptResponseFilterFactory extends AbstractGatewayFilterFactory<EncryptResponseFilterFactory.Config> {
  public EncryptResponseFilterFactory() {
    super(Config.class);
  }

  @Override
  public GatewayFilter apply(Config config) {
    EncryptResponseFilter gatewayFilter = new EncryptResponseFilter(config);
    gatewayFilter.setFactory(this);
    return gatewayFilter;
  }

  @Data
  public static class Config {
    private String publicKey;
    private String privateKey;
    private List<String> whiteList;
    private List<String> blackList;
  }

  public class EncryptResponseFilter implements GatewayFilter, Ordered {
    private final Config config;
    private GatewayFilterFactory<Config> gatewayFilterFactory;

    public EncryptResponseFilter(Config config) {
      this.config = config;
    }


    private boolean shouldEncrypt(ServerWebExchange exchange) {
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
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
      if (!shouldEncrypt(exchange)) {
        return chain.filter(exchange);
      }

      return chain.filter(exchange.mutate().response(new EncryptResponseServerHttpResponse(exchange, this.config)).build());
    }

    public void setFactory(GatewayFilterFactory<Config> gatewayFilterFactory) {
      this.gatewayFilterFactory = gatewayFilterFactory;
    }

    @Override
    public int getOrder() {
      return Ordered.HIGHEST_PRECEDENCE;
    }
  }

  @Slf4j
  protected static class EncryptResponseServerHttpResponse extends ServerHttpResponseDecorator {
    private final ServerWebExchange exchange;
    private final Config config;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public EncryptResponseServerHttpResponse(ServerWebExchange exchange, Config config) {
      super(exchange.getResponse());
      this.exchange = exchange;
      this.config = config;
    }

    @Override
    public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
      Flux<DataBuffer> bufferFlux = Flux.from(body);
      Mono<byte[]> data = DataBufferUtils.join(bufferFlux).map(bytes -> {
        byte[] array = new byte[bytes.readableByteCount()];
        bytes.read(array);
        DataBufferUtils.release(bytes);
        return array;
      });


      Mono<String> content = data.map(bytes -> new String(bytes, StandardCharsets.UTF_8));

      Mono<EncryptBody> encryptedContent = content.flatMap(c -> {
        try {
          return Mono.just(EncryptBodyUtil.encrypt(c, config.getPublicKey()));
        } catch (Exception e) {
          log.error("Failed to encrypt response body", e);
          return Mono.error(e);
        }
      });

      encryptedContent.subscribe(encrypted -> {
        log.info("Encrypted response body: {}", encrypted);
      });

      //return super.writeWith(encryptedContent.map(encrypted -> {
      //  byte[] bytes = encrypted.getBody().getBytes(StandardCharsets.UTF_8);
      //  DataBuffer buffer = this.bufferFactory().wrap(bytes);
      //  getDelegate().getHeaders().setContentLength(bytes.length);
      //  return buffer;
      //}).onErrorResume(e -> {
      //  String errorMessage = "Error encrypting response body: " + e.getMessage();
      //  log.error(errorMessage, e);
      //  DataBuffer buffer = this.bufferFactory().wrap(errorMessage.getBytes(StandardCharsets.UTF_8));
      //  getDelegate().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
      //  return getDelegate().writeWith(Flux.just(buffer));
      //}));

      return getDelegate().writeWith(body);

      //return super.writeWith(fluxBody.buffer().map(dataBuffers -> {
      //  DataBufferFactory dataBufferFactory = new DefaultDataBufferFactory();
      //  DataBuffer dataBuffer = dataBufferFactory.join(dataBuffers);
      //  byte[] content = new byte[dataBuffer.readableByteCount()];
      //  dataBuffer.read(content);
      //  DataBufferUtils.release(dataBuffer);
      //  String data = new String(content, Charsets.UTF_8);
      //
      //  //try {
      //  //  log.info("加密响应数据 开始 ：{}", data);
      //  //  EncryptBody encryptBody = EncryptBodyUtil.encrypt(data, config.getPublicKey());
      //  //  log.info("加密响应数据 完成 ：{}", encryptBody);
      //  //
      //  //  byte[] encryptBodyBytes = objectMapper.writeValueAsBytes(encryptBody);
      //  //  return this.getDelegate().writeWith(encryptBodyBytes);
      //  //
      //  //
      //  //  response.getHeaders().setContentLength(encryptBodyBytes.length);
      //  //  return bufferFactory.wrap(encryptBodyBytes);
      //  //} catch (Exception e) {
      //  //  log.error("Failed to encrypt the response body", e);
      //  //  response.getHeaders().setContentLength(0L);
      //  //  return bufferFactory.allocateBuffer();
      //  //}
      //
      //  return getDelegate().writeWith(body);
    }


    @Override
    public Mono<Void> writeAndFlushWith(Publisher<? extends Publisher<? extends DataBuffer>> body) {
      return writeWith(Flux.from(body).flatMapSequential(p -> p));
    }
  }

}
