package com.github.lalifeier.mall.cloud.demo.applicaiton.book.service;

import com.github.lalifeier.mall.cloud.common.model.ddd.QueryApplicationService;
import com.github.lalifeier.mall.cloud.common.model.query.PageQuery;
import com.github.lalifeier.mall.cloud.common.model.query.Pagination;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.model.dto.BookDTO;

public interface BookQueryApplicationService extends QueryApplicationService {
  BookDTO get(Long id);

  Pagination<BookDTO> query(PageQuery query);
}
