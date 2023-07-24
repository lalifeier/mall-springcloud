package com.github.lalifeier.mall.cloud.demo.applicaiton.book.service.repository;

import com.github.lalifeier.mall.cloud.common.model.query.Pagination;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.entity.Book;
import com.github.lalifeier.mall.cloud.demo.interfaces.rest.book.model.request.BookPageQuery;
import com.github.lalifeier.mall.cloud.demo.interfaces.rest.book.model.request.BookQuery;

public interface BookQueryRepository {
  Pagination<Book> query(BookPageQuery query);

  Long count(BookQuery query);
}
