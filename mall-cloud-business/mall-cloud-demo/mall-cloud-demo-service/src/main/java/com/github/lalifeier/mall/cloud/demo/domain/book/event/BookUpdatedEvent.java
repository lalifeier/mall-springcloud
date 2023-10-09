package com.github.lalifeier.mall.cloud.demo.domain.book.event;

import com.github.lalifeier.mall.cloud.common.event.BaseDomainEvent;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.entity.Book;

public class BookUpdatedEvent extends BaseDomainEvent<Book> {
    public BookUpdatedEvent(Book eventData) {
        super(eventData);
    }
}
