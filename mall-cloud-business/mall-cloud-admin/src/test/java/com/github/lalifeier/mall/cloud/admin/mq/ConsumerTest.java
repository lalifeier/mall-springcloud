package com.github.lalifeier.mall.cloud.admin.mq;

import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

//@Slf4j
//@SpringBootTest
//public class ConsumerTest {
//
//    @Bean
//    public Consumer<String> sink1() {
//        return message -> System.out.println("收到消息:" + message);
//    }
//}
