package com.github.lalifeier.mall.cloud.demo.applicaiton.book.event.publish;

import org.springframework.context.annotation.Bean;

import java.util.function.Supplier;

public class BookProducer {

  @Bean
  public Supplier<String> source1() {
    return () -> "Hello, world!";
  }
}
