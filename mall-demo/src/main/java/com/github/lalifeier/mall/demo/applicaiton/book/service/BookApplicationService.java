package com.github.lalifeier.mall.demo.applicaiton.book.service;

import java.util.List;

import com.github.lalifeier.mall.common.model.PageList;
import com.github.lalifeier.mall.demo.applicaiton.book.bo.BookBO;
import com.github.lalifeier.mall.demo.applicaiton.book.bo.CreateBookBO;
import com.github.lalifeier.mall.demo.applicaiton.book.bo.UpdateBookBO;

public interface BookApplicationService {
  void createBook(CreateBookBO createBookBO);

  void updateBook(UpdateBookBO updateBookBO);

  void deleteBook(Long id);

  BookBO getBookById(Long id);

  PageList<BookBO> getBooks(int pageNum, int pageSize);

  List<BookBO> getAllBooks();
}
