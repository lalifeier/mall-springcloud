package com.github.lalifeier.mall.cloud.gateway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lalifeier.mall.cloud.common.utils.RSAUtil;
import com.github.lalifeier.mall.cloud.gateway.utils.WebFluxUtil;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
public class ResponseEncryptFilter implements GlobalFilter, Ordered {

  private String publicKey;
  private List<String> whiteList;
  private List<String> blackList;

  private static final ObjectMapper objectMapper = new ObjectMapper();

  public ResponseEncryptFilter(String publicKey, List<String> whiteList, List<String> blackList) {
    this.publicKey = publicKey;
    this.whiteList = whiteList;
    this.blackList = blackList;
  }

  private boolean shouldEncrypt(ServerWebExchange exchange) {
    ServerHttpRequest request = exchange.getRequest();
    String path = request.getPath().toString();

    // 判断白名单和黑名单
    if (WebFluxUtil.isPathMatch(path, whiteList)) {
      return false;
    }
    if (!WebFluxUtil.isPathMatch(path, blackList)) {
      return false;
    }

    // 判断是否为 JSON 请求
    if (!WebFluxUtil.isJsonRequest(exchange)) {
      return false;
    }

    return true;
  }

  private Optional<String> getRawData(ServerWebExchange exchange) {
    byte[] rawBody = exchange.getAttributeOrDefault(ServerWebExchangeUtils.CACHED_REQUEST_BODY_ATTR, new byte[0]);
    try {
      Map<String, Object> responseBodyMap = objectMapper.readValue(rawBody, new TypeReference<Map<String, Object>>() {
      });
      return Optional.ofNullable((String) responseBodyMap.get("data"));
    } catch (IOException e) {
      return Optional.empty();
    }
  }

  private byte[] encryptRawData(String rawData) throws Exception {
    String encryptedData = RSAUtil.encrypt(publicKey, rawData);
    Map<String, Object> responseBodyMap = null;
    try {
      responseBodyMap = objectMapper.readValue(rawData, new TypeReference<Map<String, Object>>() {
      });
      responseBodyMap.put("data", encryptedData);
      return objectMapper.writeValueAsBytes(responseBodyMap);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    if (!shouldEncrypt(exchange)) {
      return chain.filter(exchange);
    }

    Optional<String> rawDataOptional = getRawData(exchange);
    if (rawDataOptional.isEmpty()) {
      return chain.filter(exchange);
    }
    String rawData = rawDataOptional.get();

    byte[] encryptedBody = new byte[0];
    try {
      encryptedBody = encryptRawData(rawData);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    ServerHttpResponse response = exchange.getResponse();
    HttpStatus statusCode = response.getStatusCode();
    HttpHeaders headers = response.getHeaders();

    ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(response) {
      @NotNull
      @Override
      public Mono<Void> writeWith(@NotNull Publisher<? extends DataBuffer> body) {
        DataBuffer buffer = getDelegate().bufferFactory().wrap(encryptedBody);
        return super.writeWith(Mono.just(buffer));
      }
    };
    decoratedResponse.setStatusCode(statusCode);
    decoratedResponse.getHeaders().putAll(headers);
    decoratedResponse.getHeaders().setContentType(MediaType.APPLICATION_JSON);

    // 返回新的响应
    return chain.filter(exchange.mutate().response(decoratedResponse).build());
  }

  @Override
  public int getOrder() {
    return Ordered.LOWEST_PRECEDENCE;
  }
}
