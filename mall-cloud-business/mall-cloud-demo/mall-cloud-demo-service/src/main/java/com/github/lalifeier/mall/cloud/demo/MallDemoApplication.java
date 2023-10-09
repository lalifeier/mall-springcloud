package com.github.lalifeier.mall.cloud.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

// @EnableDubbo
// @EnableSignature
// @EnableRateLimiter
@EnableAsync
@EnableRetry
@EnableDiscoveryClient
@SpringBootApplication
public class MallDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallDemoApplication.class, args);
    }
}
