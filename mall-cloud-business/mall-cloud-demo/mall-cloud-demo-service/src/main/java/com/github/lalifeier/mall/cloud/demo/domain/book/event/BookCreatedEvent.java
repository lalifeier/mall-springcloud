package com.github.lalifeier.mall.cloud.demo.domain.book.event;

import com.github.lalifeier.mall.cloud.common.event.BaseDomainEvent;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.entity.Book;

public class BookCreatedEvent extends BaseDomainEvent<Book> {
    public BookCreatedEvent(Book eventData) {
        super(eventData);
    }
}
