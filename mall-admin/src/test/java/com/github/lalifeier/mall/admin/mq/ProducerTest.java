package com.github.lalifeier.mall.admin.mq;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@Slf4j
@SpringBootTest
public class Producer {

    @Autowired
    private StreamBridge streamBridge;

    @Bean
    public Supplier<String> source1() {
        return () -> "测试定时消息";
    }

    @Test
    public void send() {
        streamBridge.send("source1-out-0", new Date());
    }
}
