package com.github.lalifeier.mall.cloud.demo;

import com.github.lalifeier.mall.cloud.ratelimiter.annotation.EnableRateLimiter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@EnableDubbo

@EnableRateLimiter
@SpringBootApplication
@EnableDiscoveryClient
public class MallDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(MallDemoApplication.class, args);
  }
}
