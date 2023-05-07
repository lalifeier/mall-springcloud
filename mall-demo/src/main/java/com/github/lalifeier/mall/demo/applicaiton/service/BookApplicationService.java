package com.github.lalifeier.mall.demo.applicaiton.service;

import java.util.List;

import com.github.lalifeier.mall.common.model.PageList;
import com.github.lalifeier.mall.demo.interfaces.web.book.model.request.CreateBookRequest;
import com.github.lalifeier.mall.demo.interfaces.web.book.model.request.UpdateBookRequest;
import com.github.lalifeier.mall.demo.interfaces.web.book.model.response.BookResponse;

public interface BookApplicationService {
  void createBook(CreateBookRequest request);

  void updateBook(UpdateBookRequest request);

  void deleteBook(Long id);

  BookResponse getBookById(Long id);

  PageList<BookResponse> getBooks(int pageNum, int pageSize);

  List<BookResponse> getAllBooks();
}
