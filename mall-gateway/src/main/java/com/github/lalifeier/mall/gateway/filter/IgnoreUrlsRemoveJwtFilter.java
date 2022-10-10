package com.github.lalifeier.mall.gateway.filter;

import com.github.lalifeier.mall.gateway.config.IgnoreUrlsConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Component
public class IgnoreUrlsRemoveJwtFilter implements WebFilter {

    private final IgnoreUrlsConfig ignoreUrlsConfig;

    public IgnoreUrlsRemoveJwtFilter(IgnoreUrlsConfig ignoreUrlsConfig) {
        this.ignoreUrlsConfig = ignoreUrlsConfig;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        PathMatcher pathMatcher = new AntPathMatcher();
        List<String> ignoreUrls = ignoreUrlsConfig.getUrls();
        //白名单路径移除JWT请求头
        for (String ignoreUrl : ignoreUrls) {
            if (pathMatcher.match(ignoreUrl, path)) {
                //request = exchange.getRequest().mutate().header("Authorization", "").build();
                exchange = exchange.mutate().request(request).build();
                return chain.filter(exchange);
            }
        }
        return chain.filter(exchange);
    }
}
