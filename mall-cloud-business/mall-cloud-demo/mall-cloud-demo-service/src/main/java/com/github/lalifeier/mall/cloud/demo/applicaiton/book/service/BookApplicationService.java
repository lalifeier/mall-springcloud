package com.github.lalifeier.mall.cloud.demo.applicaiton.book.service;

import com.github.lalifeier.mall.cloud.common.model.PageList;
import com.github.lalifeier.mall.cloud.common.model.PageRequest;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.bo.BookBO;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.bo.CreateBookBO;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.bo.UpdateBookBO;

import java.util.List;

public interface BookApplicationService {
  void createBook(CreateBookBO createBookBO);

  void updateBook(UpdateBookBO updateBookBO);

  void deleteBook(Long id);

  BookBO getBookById(Long id);

  PageList<BookBO> getBooks(PageRequest request);

  List<BookBO> getAllBooks();
}
