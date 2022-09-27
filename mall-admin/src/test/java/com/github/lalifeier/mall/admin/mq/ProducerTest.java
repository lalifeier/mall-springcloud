package com.github.lalifeier.mall.admin.mq;

import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.function.Supplier;

@Slf4j
@SpringBootTest
public class ProducerTest {

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
