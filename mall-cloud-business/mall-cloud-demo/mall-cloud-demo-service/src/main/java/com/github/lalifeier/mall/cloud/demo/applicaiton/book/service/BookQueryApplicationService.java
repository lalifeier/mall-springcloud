package com.github.lalifeier.mall.cloud.demo.applicaiton.book.service;

import com.github.lalifeier.mall.cloud.common.model.marker.QueryApplicationService;
import com.github.lalifeier.mall.cloud.common.model.query.Pagination;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.model.dto.BookDTO;
import com.github.lalifeier.mall.cloud.demo.interfaces.rest.book.model.request.BookPageQuery;

public interface BookQueryApplicationService extends QueryApplicationService {
  BookDTO get(Long id);

  Pagination<BookDTO> query(BookPageQuery query);
}
