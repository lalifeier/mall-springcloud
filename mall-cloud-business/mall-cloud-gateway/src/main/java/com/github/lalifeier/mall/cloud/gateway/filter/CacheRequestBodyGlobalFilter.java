package com.github.lalifeier.mall.cloud.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class CacheRequestBodyGlobalFilter implements GlobalFilter, Ordered {

  // public static final String CACHED_REQUEST_BODY_X_WWW_FORM_URLENCODED_MAP_KEY =
  // "cachedXWwwFormUrlEncodedMap";
  // public static final String CACHED_REQUEST_BODY_FORM_DATA_MAP_KEY =
  // "cachedRequestBodyFormDataMap";

  // public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
  // Supplier<? extends Mono<? extends Void>> supplier = () -> chain.filter(exchange);
  //
  // MediaType contentType = exchange.getRequest().getHeaders().getContentType();
  // if (MediaType.APPLICATION_FORM_URLENCODED.isCompatibleWith(contentType)) {
  // return ServerWebExchangeUtils.cacheRequestBodyAndRequest(exchange,
  // (serverHttpRequest) -> ServerRequest
  // .create(exchange.mutate().request(serverHttpRequest).build(),
  // HandlerStrategies.withDefaults().messageReaders())
  // .bodyToMono(new ParameterizedTypeReference<MultiValueMap<String, String>>() {})
  // .doOnNext(map -> {
  // exchange.getAttributes().put(CACHED_REQUEST_BODY_X_WWW_FORM_URLENCODED_MAP_KEY,
  // map);
  // }))
  // .then(Mono.defer(supplier));
  // } else if (MediaType.MULTIPART_FORM_DATA.isCompatibleWith(contentType)) {
  // return ServerWebExchangeUtils.cacheRequestBodyAndRequest(exchange,
  // (serverHttpRequest) -> ServerRequest
  // .create(exchange.mutate().request(serverHttpRequest).build(),
  // HandlerStrategies.withDefaults().messageReaders())
  // .bodyToMono(new ParameterizedTypeReference<MultiValueMap<String, Part>>() {})
  // .doOnNext(map -> {
  // exchange.getAttributes().put(CACHED_REQUEST_BODY_FORM_DATA_MAP_KEY, map);
  // }))
  // .then(Mono.defer(supplier));
  // } else {
  // return chain.filter(exchange);
  // }
  // }

  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    HttpMethod method = exchange.getRequest().getMethod();
    if (method == null || method == HttpMethod.GET || method == HttpMethod.DELETE) {
      return chain.filter(exchange);
    }

    return ServerWebExchangeUtils.cacheRequestBodyAndRequest(exchange, (serverHttpRequest) -> {
      if (serverHttpRequest == exchange.getRequest()) {
        return chain.filter(exchange);
      }
      return chain.filter(exchange.mutate().request(serverHttpRequest).build());
    });
  }

  @Override
  public int getOrder() {
    return Ordered.HIGHEST_PRECEDENCE + 1; // After RemoveCachedBodyFilter, before
                                           // AdaptCachedBodyGlobalFilter
  }
}
