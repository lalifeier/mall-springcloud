package com.github.lalifeier.mall.cloud.gateway.filter;

import com.github.lalifeier.mall.cloud.gateway.properties.SignProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Order(0)
@Component
@AllArgsConstructor
public class SignFilter implements GlobalFilter, Ordered {

  // private static final String SHOULD_NOT_FILTER = "SHOULD_NOT_FILTER" +
  // CsrfFilter.class.getName();

  private final SignProperties signProperties;
  private final RedisTemplate<String, Object> redisTemplate;

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    return chain.filter(exchange);

    //
    // if (!signProperties.getEnabled()) {
    // return chain.filter(exchange);
    // }
    //
    // ServerHttpRequest request = exchange.getRequest();
    //
    //// 过滤不需要签名验证的地址
    // Set<String> uriSet = new HashSet<>(signProperties.getWhiteUrls());
    // String requestUri = request.getURI().getPath();
    // for (String uri : uriSet) {
    // if (requestUri.contains(uri)) {
    // return chain.filter(exchange);
    // }
    // }
    //
    //// 获取时间戳
    // String timestamp = request.getHeaders().getFirst(Common.TIMESTAMP);
    //// 获取随机字符串
    // String nonce = request.getHeaders().getFirst(Common.NONCE);
    //// 获取签名
    // String signature = request.getHeaders().getFirst(Common.SIGN);
    // log.debug("timestamp: {} nonce: {}, signature: {}", timestamp, nonce, signature);
    //
    //// 判断时间是否大于 xx 秒（防止重放攻击）
    // long NONCE_STR_TIMEOUT_SECONDS = 60L;
    // if (StringUtils.isBlank(timestamp) || Long.parseLong(timestamp) + (1000 *
    // NONCE_STR_TIMEOUT_SECONDS) > System.currentTimeMillis()) {
    // throw new IllegalArgumentException("invalid timestamp");
    // }
    //
    //// 判断该用户的 nonce 参数是否已经在 redis 中（防止短时间内的重放攻击）
    // final Boolean haveNonce = redisTemplate.hasKey(nonce);
    // if (StringUtils.isBlank(nonce) || Objects.isNull(haveNonce) || haveNonce) {
    // throw new IllegalArgumentException("invalid nonce");
    // }
    //
    // Flux<DataBuffer> dataBufferFlux = request.getBody();
    // String requestPayload = FilterRequestResponseUtil.resolveBodyFromRequest(dataBufferFlux);
    //
    //// 对请求头参数进行签名
    // if (StringUtils.isBlank(signature) || !StringUtils.equals(signature,
    // SignUtil.generateSign(timestamp, nonce, requestPayload, request))) {
    // throw new IllegalArgumentException("invalid signature");
    // }
    //
    //// 将本次用户请求的 nonceStr 参数存到 redis 中设置 xx 秒后自动删除
    // redisTemplate.opsForValue().set(nonce, nonce, NONCE_STR_TIMEOUT_SECONDS,
    // TimeUnit.SECONDS);
    //
    // return chain.filter(exchange);
  }

  @Override
  public int getOrder() {
    return -1;
  }
}
