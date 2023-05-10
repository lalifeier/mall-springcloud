package com.github.lalifeier.mall.demo.infrastructure.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "baidu", url = "http://www.baidu.com", fallback = FeignTestFallback.class)
public interface FeignTestClient {

    @GetMapping("/s")
    String search(@RequestParam("wd") String wd);
}
