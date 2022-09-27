package com.github.lalifeier.mall.admin;

import com.github.lalifeier.mall.common.advice.GlobalExceptionAdvice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

//@SpringBootApplication(scanBasePackages = "com.github.lalifeier.mall")
@SpringBootApplication
@EnableDiscoveryClient
//@Import(value = {GlobalExceptionAdvice.class})
public class MallAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallAdminApplication.class, args);
    }
}
