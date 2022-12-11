package com.github.lalifeier.mall.demo.infrastructure.feign;

import org.springframework.stereotype.Component;

@Component
public class FeignTestFallback implements FeignTestClient {

    @Override
    public String search(String wd) {
        return "请求异常报错了";
    }
}