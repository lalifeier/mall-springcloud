package com.github.lalifeier.mall.demo.applicaiton.service;

import java.util.List;

import com.github.lalifeier.mall.common.model.PageList;
import com.github.lalifeier.mall.demo.presentation.rest.book.dto.request.CreateBookRequest;
import com.github.lalifeier.mall.demo.presentation.rest.book.dto.request.UpdateBookRequest;
import com.github.lalifeier.mall.demo.presentation.rest.book.dto.response.BookResponse;

public interface BookApplicationService {
  void createBook(CreateBookRequest request);

  void updateBook(UpdateBookRequest request);

  void deleteBook(Long id);

  BookResponse getBookById(Long id);

  PageList<BookResponse> getBooks(int pageNum, int pageSize);

  List<BookResponse> getAllBooks();
}
