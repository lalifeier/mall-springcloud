package com.github.lalifeier.mall.cloud.demo.applicaiton.book.query;

import com.github.lalifeier.mall.cloud.common.model.PageList;
import com.github.lalifeier.mall.cloud.common.model.PageQuery;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.dto.BookDTO;

import java.util.List;

public interface BookQueryApplicationService {
  BookDTO getBookById(Long id);

  PageList<BookDTO> getBooks(PageQuery query);

  List<BookDTO> getAllBooks();
}
