package com.github.lalifeier.mall.cloud.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@EnableDubbo
@SpringBootApplication
@EnableDiscoveryClient
public class MallDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(MallDemoApplication.class, args);
  }
}
