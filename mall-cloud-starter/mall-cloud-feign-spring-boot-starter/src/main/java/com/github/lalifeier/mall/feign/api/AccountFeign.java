package com.github.lalifeier.mall.feign.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "account-service")
public interface AccountFeign {
    @GetMapping("/account/test")
    String test();
}