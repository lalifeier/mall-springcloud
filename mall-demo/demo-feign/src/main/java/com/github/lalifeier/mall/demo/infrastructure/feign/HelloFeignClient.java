package com.github.lalifeier.mall.demo.infrastructure.feign;

import com.github.lalifeier.mall.demo.interfaces.api.HelloApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("demo-service")
public interface HelloFeignClient extends HelloApi {
}
