package com.github.lalifeier.mall.cloud.demo.applicaiton.book.service;

import com.github.lalifeier.mall.cloud.common.model.marker.QueryApplicationService;
import com.github.lalifeier.mall.cloud.common.model.result.PageResult;
import com.github.lalifeier.mall.cloud.demo.book.dto.BookDTO;
import com.github.lalifeier.mall.cloud.demo.book.dto.query.BookPageQuery;


public interface BookQueryApplicationService extends QueryApplicationService {
  BookDTO get(Long id);

  PageResult<BookDTO> query(BookPageQuery query);
}
