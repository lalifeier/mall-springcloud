package com.github.lalifeier.mall.cloud.demo.infrastructure.feign.fallback;

import com.github.lalifeier.mall.cloud.demo.infrastructure.feign.HelloClient;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class HelloClientFallback implements HelloClient {

    @Setter private Throwable cause;

    @Override
    public String hello(String name) {
        return null;
    }
}
