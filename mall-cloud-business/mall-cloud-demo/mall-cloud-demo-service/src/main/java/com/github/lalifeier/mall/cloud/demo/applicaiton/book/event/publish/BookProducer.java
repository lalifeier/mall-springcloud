package com.github.lalifeier.mall.cloud.demo.applicaiton.book.event.publish;

import java.util.function.Supplier;
import org.springframework.context.annotation.Bean;

public class BookProducer {

    @Bean
    public Supplier<String> source1() {
        return () -> "Hello, world!";
    }
}
