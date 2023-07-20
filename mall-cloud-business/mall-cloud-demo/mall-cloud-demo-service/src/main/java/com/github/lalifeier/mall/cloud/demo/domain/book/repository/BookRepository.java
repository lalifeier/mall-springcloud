package com.github.lalifeier.mall.cloud.demo.domain.book.repository;

import com.github.lalifeier.mall.cloud.common.model.marker.Repository;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.entity.Book;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.valueobject.BookId;

public interface BookRepository extends Repository<Book, BookId> {

}
