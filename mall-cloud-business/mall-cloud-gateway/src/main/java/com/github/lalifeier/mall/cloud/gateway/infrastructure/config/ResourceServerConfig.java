package com.github.lalifeier.mall.cloud.gateway.infrastructure.config;

import cn.hutool.core.util.ArrayUtil;
import com.github.lalifeier.mall.cloud.gateway.infrastructure.component.AuthorizationManager;
import com.github.lalifeier.mall.cloud.gateway.infrastructure.filter.IgnoreUrlsRemoveJwtFilter;
import com.github.lalifeier.mall.cloud.gateway.infrastructure.properties.GatewayAuthProperties;
import com.github.lalifeier.mall.cloud.common.constant.Common;
import com.github.lalifeier.mall.cloud.common.result.Result;
import com.github.lalifeier.mall.cloud.common.utils.JsonUtils;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;


@AllArgsConstructor
@Configuration
@EnableWebFluxSecurity
public class ResourceServerConfig {
  private final AuthorizationManager authorizationManager;
  private final GatewayAuthProperties gatewayAuthProperties;
  private final IgnoreUrlsRemoveJwtFilter ignoreUrlsRemoveJwtFilter;

  @Bean
  public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
    http.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter());
    //自定义处理JWT请求头过期或签名错误的结果
    http.oauth2ResourceServer().authenticationEntryPoint(authenticationEntryPoint());
    //对白名单路径，直接移除JWT请求头
    http.addFilterBefore(ignoreUrlsRemoveJwtFilter, SecurityWebFiltersOrder.AUTHENTICATION);
    http.authorizeExchange()
      //白名单配置
      .pathMatchers(ArrayUtil.toArray(gatewayAuthProperties.getWhiteUrls(), String.class)).permitAll()
      //鉴权管理器配置
      .anyExchange().access(authorizationManager)
      .and()
      .exceptionHandling()
      //处理未授权
      .accessDeniedHandler(accessDeniedHandler())
      //处理未认证
      .authenticationEntryPoint(authenticationEntryPoint())
      .and().csrf().disable();
    return http.build();
  }

  /**
   * 自定义未授权响应
   */
  @Bean
  public ServerAccessDeniedHandler accessDeniedHandler() {
    return (exchange, e) ->
      Mono.defer(() -> Mono.just(exchange.getResponse()))
        .flatMap(response -> {
          response.setStatusCode(HttpStatus.FORBIDDEN);
          response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
          String body = JsonUtils.bean2Json(Result.failure(403, e.getMessage()));
          DataBuffer buffer = response.bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
          return response.writeWith(Mono.just(buffer)).doOnError(error -> DataBufferUtils.release(buffer));
        });
  }

  /**
   * 自定义未认证响应
   */
  @Bean
  public ServerAuthenticationEntryPoint authenticationEntryPoint() {
    return (exchange, e) ->
      Mono.defer(() -> Mono.just(exchange.getResponse()))
        .flatMap(response -> {
          response.setStatusCode(HttpStatus.UNAUTHORIZED);
          response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
          String body = JsonUtils.bean2Json(Result.failure(401, e.getMessage()));
          DataBuffer buffer = response.bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
          return response.writeWith(Mono.just(buffer)).doOnError(error -> DataBufferUtils.release(buffer));
        });
  }

  @Bean
  public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
    JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
    jwtGrantedAuthoritiesConverter.setAuthorityPrefix(Common.AUTHORITY_PREFIX);
    jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(Common.AUTHORITY_CLAIM_NAME);
    JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
    jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
    return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
  }
}
