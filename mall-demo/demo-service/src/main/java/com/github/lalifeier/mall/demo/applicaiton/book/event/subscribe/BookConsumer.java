package com.github.lalifeier.mall.demo.applicaiton.book.event.subscribe;

import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

public class BookConsumer {
  @Bean
  public Consumer<String> sink1() {
    return message -> System.out.println("Received message: " + message);
  }
}
