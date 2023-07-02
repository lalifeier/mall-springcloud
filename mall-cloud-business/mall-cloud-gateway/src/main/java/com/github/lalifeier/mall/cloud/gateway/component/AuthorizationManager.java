package com.github.lalifeier.mall.cloud.gateway.component;

//import cn.hutool.core.convert.Convert;
//import com.github.lalifeier.mall.cloud.common.constant.Constants;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.security.authorization.AuthorizationDecision;
//import org.springframework.security.authorization.ReactiveAuthorizationManager;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.web.server.authorization.AuthorizationContext;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Mono;
//
//import java.net.URI;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Slf4j
//@RequiredArgsConstructor
//@Component
//public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {
//  private final RedisTemplate<String, Object> redisTemplate;
//
//  @Override
//  public Mono<AuthorizationDecision> check(Mono<Authentication> authenticationMono, AuthorizationContext authorizationContext) {
//    ServerHttpRequest request = authorizationContext.getExchange().getRequest();
//    if (request.getMethod() == HttpMethod.OPTIONS) {
//      return Mono.just(new AuthorizationDecision(true));
//    }
//
//    //从Redis中获取当前路径可访问角色列表
//    URI uri = authorizationContext.getExchange().getRequest().getURI();
//    Object obj = redisTemplate.opsForHash().get(Constants.RESOURCE_ROLES_MAP, uri.getPath());
//    //List<String> authorities = (List<String>) ConvertUtils.convert(obj, String.class)l
//    List<String> authorities = Convert.toList(String.class, obj);
//    authorities = authorities.stream().map(i -> i = Constants.AUTHORITY_PREFIX + i).collect(Collectors.toList());
//
//    //认证通过且角色匹配的用户可访问当前路径
//    return authenticationMono
//      .filter(Authentication::isAuthenticated)
//      .flatMapIterable(Authentication::getAuthorities)
//      .map(GrantedAuthority::getAuthority)
//      .any(authorities::contains)
//      .map(AuthorizationDecision::new)
//      .defaultIfEmpty(new AuthorizationDecision(false));
//  }
//}
