package com.github.lalifeier.mall.admin.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootTest
public class Consumer {


    @Bean
    public Consumer<String> sink1() {
        return message -> System.out.println("收到消息:" + message);
    }
}
