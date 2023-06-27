package com.github.lalifeier.mall.cloud.demo.domain.book.service;

import com.github.lalifeier.mall.cloud.demo.domain.book.model.entity.BookEntity;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.valueobject.BookId;

public interface BookDomainService {
  void create(BookEntity bookEntity);

  void update(BookEntity bookEntity);

  BookEntity get(BookId bookId);

  void delete(BookId bookId);

  //List<BookDO> getAllBooks();
  //PageList<BookDO> getBooks(int pageNum, int pageSize);
}
