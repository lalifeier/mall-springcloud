package com.github.lalifeier.mall.cloud.demo.api;

import org.springframework.web.bind.annotation.GetMapping;

public interface HelloApi {

    @GetMapping("/hello")
    String hello(String name);
}
