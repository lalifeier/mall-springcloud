package com.github.lalifeier.mall.cloud.demo.infrastructure.feign;

import com.github.lalifeier.mall.cloud.demo.book.HelloApi;
import com.github.lalifeier.mall.cloud.demo.infrastructure.feign.fallback.HelloClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@Component
@FeignClient(name = "demo-service", path = "/", fallback = HelloClientFallbackFactory.class)
public interface HelloClient extends HelloApi {}
