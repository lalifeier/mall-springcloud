package com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@SpringBootApplication(scanBasePackages = "com.github.lalifeier.mall")
@SpringBootApplication
@EnableDiscoveryClient
//@Import(value = {GlobalExceptionAdvice.class})
public class MallAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallAdminApplication.class, args);
    }
}
