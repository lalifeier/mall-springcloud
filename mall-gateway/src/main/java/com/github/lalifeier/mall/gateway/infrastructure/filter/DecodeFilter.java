package com.github.lalifeier.mall.gateway.infrastructure.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class DecodeFilter implements GatewayFilter {

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    ServerHttpRequest request = exchange.getRequest();
    Map<String, List<String>> queryParams = request.getQueryParams();
    queryParams.forEach((k, v) -> {
      List<String> decodedValues = v.stream().map(this::decode).collect(Collectors.toList());
      request.getQueryParams().put(k, decodedValues);
    });
    return chain.filter(exchange);
  }

  private String decode(String value) {
    try {
      return URLDecoder.decode(value, "UTF-8");
    } catch (Exception ex) {
      return value;
    }
  }
}
