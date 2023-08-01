package com.github.lalifeier.mall.cloud.demo.applicaiton.book.service.repository;

import com.github.lalifeier.mall.cloud.common.model.marker.QueryRepository;
import com.github.lalifeier.mall.cloud.common.model.query.Pagination;
import com.github.lalifeier.mall.cloud.demo.api.book.model.query.BookPageQuery;
import com.github.lalifeier.mall.cloud.demo.api.book.model.query.BookQuery;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.entity.Book;

public interface BookQueryRepository extends QueryRepository {
  Pagination<Book> query(BookPageQuery query);

  Long count(BookQuery query);
}
