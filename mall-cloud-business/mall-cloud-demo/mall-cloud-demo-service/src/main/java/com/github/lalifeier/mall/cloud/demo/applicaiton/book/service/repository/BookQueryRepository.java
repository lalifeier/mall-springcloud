package com.github.lalifeier.mall.cloud.demo.applicaiton.book.service.repository;

import com.github.lalifeier.mall.cloud.common.model.query.AbstractPageQuery;
import com.github.lalifeier.mall.cloud.common.model.query.Pagination;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.entity.Book;

public interface BookQueryRepository {
  Pagination<Book> query(AbstractPageQuery query);
}
