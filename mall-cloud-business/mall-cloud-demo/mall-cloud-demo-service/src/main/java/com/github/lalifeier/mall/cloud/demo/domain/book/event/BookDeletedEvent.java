package com.github.lalifeier.mall.cloud.demo.domain.book.event;

import com.github.lalifeier.mall.cloud.common.event.BaseDomainEvent;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.entity.Book;

public class BookDeletedEvent extends BaseDomainEvent<Book> {
    public BookDeletedEvent(Book eventData) {
        super(eventData);
    }
}
