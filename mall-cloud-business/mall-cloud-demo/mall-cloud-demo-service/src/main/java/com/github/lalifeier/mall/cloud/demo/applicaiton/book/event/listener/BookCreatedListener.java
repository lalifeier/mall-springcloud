package com.github.lalifeier.mall.cloud.demo.applicaiton.book.event.listener;

import com.github.lalifeier.mall.cloud.demo.domain.book.event.BookCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookCreatedListener {
    @Order(1)
    @Async
    @EventListener
    // @Retryable(value = Exception.class, maxAttempts = 3,
    // backoff = @Backoff(delay = 2000, multiplier = 1.5))
    public void handleEvent(BookCreatedEvent event) {
        log.info("bookEvent: {}", event);
    }
}
