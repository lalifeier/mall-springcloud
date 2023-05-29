package com.github.lalifeier.mall.cloud.gateway.infrastructure.filter;

import com.github.lalifeier.mall.common.constant.Common;
import com.nimbusds.jose.JWSObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.text.ParseException;

@Slf4j
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    String token = exchange.getRequest().getHeaders().getFirst("Authorization");
    if (StringUtils.isBlank(token)) {
      return chain.filter(exchange);
    }

    try {
      //从token中解析用户信息并设置到Header中去
      String realToken = token.replace(Common.BEARER_TYPE, "").trim();
      log.info("Authorization：{}", realToken);
      JWSObject jwsObject = JWSObject.parse(realToken);
      String userStr = jwsObject.getPayload().toString();
      log.info("AuthGlobalFilter.filter() user:{}", userStr);
      ServerHttpRequest request = exchange.getRequest().mutate().header("user", userStr).build();
      exchange = exchange.mutate().request(request).build();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return chain.filter(exchange);
  }

  @Override
  public int getOrder() {
    return 0;
  }

}
