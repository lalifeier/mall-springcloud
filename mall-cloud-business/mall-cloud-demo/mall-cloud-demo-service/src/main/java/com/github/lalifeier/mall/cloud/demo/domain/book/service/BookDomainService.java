package com.github.lalifeier.mall.cloud.demo.domain.book.service;

import com.github.lalifeier.mall.cloud.demo.domain.book.model.entity.BookEntity;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.valueobject.BookId;

public interface BookDomainService {
  void createBook(BookEntity bookEntity);

  void updateBook(BookEntity bookEntity);

  BookEntity getBookById(BookId bookId);

  void deleteBook(BookId bookId);

  //List<BookDO> getAllBooks();
  //PageList<BookDO> getBooks(int pageNum, int pageSize);
}
