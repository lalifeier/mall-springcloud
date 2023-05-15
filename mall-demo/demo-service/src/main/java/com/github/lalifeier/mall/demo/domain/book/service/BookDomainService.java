package com.github.lalifeier.mall.demo.domain.book.service;

import com.github.lalifeier.mall.demo.domain.book.model.entity.BookDO;
import com.github.lalifeier.mall.demo.domain.book.model.valueobject.BookId;

public interface BookDomainService {
  void createBook(BookDO bookDO);

  void updateBook(BookDO bookDO);

  BookDO getBookById(BookId bookId);

  void deleteBook(BookId bookId);

  //List<BookDO> getAllBooks();
  //PageList<BookDO> getBooks(int pageNum, int pageSize);
}
