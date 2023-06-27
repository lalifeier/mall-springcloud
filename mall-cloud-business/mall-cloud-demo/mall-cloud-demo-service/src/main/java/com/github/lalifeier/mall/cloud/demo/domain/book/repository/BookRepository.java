package com.github.lalifeier.mall.cloud.demo.domain.book.repository;

import com.github.lalifeier.mall.cloud.common.model.ddd.Repository;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.entity.BookEntity;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.valueobject.BookId;

public interface BookRepository extends Repository<BookEntity, BookId> {

}
