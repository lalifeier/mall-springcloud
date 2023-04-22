package com.github.lalifeier.mall.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MallAccountApplication {

  public static void main(String[] args) {
    SpringApplication.run(MallAccountApplication.class, args);
  }
}
