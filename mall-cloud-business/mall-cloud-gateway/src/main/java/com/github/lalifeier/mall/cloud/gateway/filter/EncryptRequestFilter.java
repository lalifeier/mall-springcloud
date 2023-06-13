package com.github.lalifeier.mall.cloud.gateway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lalifeier.mall.cloud.common.utils.EncryptBodyUtil;
import com.github.lalifeier.mall.cloud.gateway.utils.WebFluxUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;

import java.util.List;

@Slf4j
@Component
public class EncryptRequestFilter extends AbstractGatewayFilterFactory<EncryptRequestFilter.Config> implements Ordered {

  public EncryptRequestFilter() {
    super(Config.class);
  }

  private static final ObjectMapper objectMapper = new ObjectMapper();

  private boolean shouldEncrypt(ServerWebExchange exchange, Config config) {
    ServerHttpRequest request = exchange.getRequest();
    String path = request.getPath().toString();

    // 判断白名单和黑名单
    if (WebFluxUtil.isPathMatch(path, config.getWhiteList())
      || !WebFluxUtil.isPathMatch(path, config.getBlackList())) {
      return false;
    }

    // 判断是否为 JSON 请求
    if (!WebFluxUtil.isJsonRequest(exchange)) {
      return false;
    }

    HttpMethod method = request.getMethod();
    if (method != HttpMethod.POST) {
      return false;
    }

    return true;
  }

  @Override
  public GatewayFilter apply(Config config) {
    return (exchange, chain) -> {
      ServerHttpRequest request = exchange.getRequest();

      if (!shouldEncrypt(exchange, config)) {
        return chain.filter(exchange);
      }

      ServerHttpRequestDecorator decorator = new ServerHttpRequestDecorator(request) {
        @Override
        public Flux<DataBuffer> getBody() {
          String rawData = WebFluxUtil.resolveBodyFromRequest(request);

          try {
            JsonNode rootNode = objectMapper.readValue(rawData, JsonNode.class);
            String encryptedData = rootNode.get("data").asText();
            String key = rootNode.get("key").asText();

            String body = EncryptBodyUtil.decrypt(encryptedData, key, config.getPrivateKey());

            byte[] decryptedBytes = objectMapper.writeValueAsBytes(body);

            DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(decryptedBytes);

            return Flux.just(buffer);
          } catch (JsonProcessingException e) {
            log.error("Error processing request body", e);
            return Flux.error(new RuntimeException("Error processing request body", e));
          } catch (Exception e) {
            log.error("Failed to decrypt the request body", e);
            return Flux.error(new RuntimeException("Failed to decrypt the request body", e));
          }
        }
      };
      return chain.filter(exchange.mutate().request(decorator).build());
    };


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
    return Ordered.LOWEST_PRECEDENCE + 1;
  }
}
