package com.github.lalifeier.mall.cloud.demo.applicaiton.book.event.subscribe;

import java.util.function.Consumer;
import org.springframework.context.annotation.Bean;

public class BookConsumer {
    @Bean
    public Consumer<String> sink1() {
        return message -> System.out.println("Received message: " + message);
    }
}
