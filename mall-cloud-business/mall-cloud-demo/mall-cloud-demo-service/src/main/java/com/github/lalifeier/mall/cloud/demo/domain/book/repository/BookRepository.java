package com.github.lalifeier.mall.cloud.demo.domain.book.repository;

import com.github.lalifeier.mall.cloud.common.model.query.Pagination;
import com.github.lalifeier.mall.cloud.common.model.query.PageQuery;
import com.github.lalifeier.mall.cloud.common.model.ddd.Repository;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.entity.BookEntity;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.valueobject.BookId;

import java.util.List;

public interface BookRepository extends Repository<BookEntity, BookId> {
  List<BookEntity> findAll();

  Pagination<BookEntity> pageList(PageQuery request);
}
