package com.github.lalifeier.mall.cloud.gateway.filter;

// import com.github.lalifeier.mall.cloud.common.constant.Constants;
// import com.github.lalifeier.mall.cloud.common.utils.JsonUtils;
// import com.google.gson.JsonObject;
// import com.nimbusds.jose.JWSObject;
// import lombok.AllArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.cloud.gateway.filter.GatewayFilterChain;
// import org.springframework.cloud.gateway.filter.GlobalFilter;
// import org.springframework.core.annotation.Order;
// import org.springframework.data.redis.core.RedisTemplate;
// import org.springframework.stereotype.Component;
// import org.springframework.util.StringUtils;
// import org.springframework.web.server.ServerWebExchange;
// import reactor.core.publisher.Mono;
//
// import java.text.ParseException;
//
// @Slf4j
// @Order(0)
// @Component
// @AllArgsConstructor
// public class GatewayRequestFilter implements GlobalFilter {
// private final RedisTemplate<String, String> redisTemplate;
//
// @Override
// public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
// //String headerToken =
// exchange.getRequest().getHeaders().getFirst(CommonConstant.JWT_HEADER_KEY);
// //if (StrUtil.isNotEmpty(headerToken)) {
// // if (isBlack(headerToken)) {
// // throw new HttpServerErrorException(HttpStatus.FORBIDDEN, "该令牌已过期，请重新获取令牌");
// // }
// //}
//
// //String rawPath = exchange.getRequest().getURI().getRawPath();
// //if (isPv(rawPath)) {
// // throw new HttpServerErrorException(HttpStatus.FORBIDDEN, "不能访问私有接口");
// //}
//
// return chain.filter(exchange);
// }
//
//
// private boolean isBlack(String headerToken) throws ParseException {
// String token = headerToken.replace(Constants.BEARER_TYPE, "").trim();
//
// JWSObject jwsObject = JWSObject.parse(token);
// String payload = jwsObject.getPayload().toString();
//
//
// JsonObject jsonObject = JsonUtils.json2Bean(payload, JsonObject.class);
//
// String jti = jsonObject.get("jti").getAsString();
// return redisTemplate.hasKey(Constants.TOKEN_BLACKLIST_PREFIX + jti);
// }
//
// private boolean isPv(String requestUri) {
// return isAccess(requestUri, "/pv");
// }
//
// private boolean isAccess(String requestURI, String access) {
// int index = requestURI.indexOf(access);
// return index >= 0 && StringUtils.countOccurrencesOf(requestURI.substring(0, index), "/") < 1;
// }
// }
