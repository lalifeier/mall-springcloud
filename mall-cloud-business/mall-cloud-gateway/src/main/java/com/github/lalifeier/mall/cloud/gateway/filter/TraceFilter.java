package com.github.lalifeier.mall.cloud.gateway.filter;

import com.github.lalifeier.mall.cloud.common.constant.HeaderConstants;
import com.github.lalifeier.mall.cloud.common.utils.MDCTraceUtil;
import com.github.lalifeier.mall.cloud.common.utils.TraceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class TraceFilter implements GlobalFilter, Ordered {
  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

    String traceId = TraceUtil.generateTraceId();

    MDCTraceUtil.setTraceId(traceId);

    ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate()
      .headers(h -> {
        h.add(HeaderConstants.TRACE_ID, traceId);
      })
      .build();

    ServerWebExchange build = exchange.mutate().request(serverHttpRequest).build();

    return chain.filter(build);
  }

  @Override
  public int getOrder() {
    return Ordered.HIGHEST_PRECEDENCE;
  }
}
