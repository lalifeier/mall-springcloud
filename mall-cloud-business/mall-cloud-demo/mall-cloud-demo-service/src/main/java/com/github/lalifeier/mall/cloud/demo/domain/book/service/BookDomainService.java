package com.github.lalifeier.mall.cloud.demo.domain.book.service;

import com.github.lalifeier.mall.cloud.demo.domain.book.model.entity.Book;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.valueobject.BookId;

public interface BookDomainService {
    void create(Book book);

    void update(Book book);

    Book get(BookId bookId);

    void delete(BookId bookId);
}
