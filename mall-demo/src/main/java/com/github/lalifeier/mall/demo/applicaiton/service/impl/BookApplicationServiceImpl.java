package com.github.lalifeier.mall.demo.applicaiton.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.lalifeier.mall.common.model.PageList;
import com.github.lalifeier.mall.demo.applicaiton.service.BookApplicationService;
import com.github.lalifeier.mall.demo.domain.book.model.valueobject.BookId;
import com.github.lalifeier.mall.demo.domain.book.service.BookDomainService;
import com.github.lalifeier.mall.demo.presentation.rest.book.dto.request.CreateBookRequest;
import com.github.lalifeier.mall.demo.presentation.rest.book.dto.request.UpdateBookRequest;
import com.github.lalifeier.mall.demo.presentation.rest.book.dto.response.BookResponse;

@Service
@Transactional
public class BookApplicationServiceImpl implements BookApplicationService {

  private final BookDomainService bookDomainService;

  public BookApplicationServiceImpl(BookDomainService bookDomainService) {

    this.bookDomainService = bookDomainService;
  }

  @Override
  public void createBook(CreateBookRequest request) {
    bookDomainService.createBook(null);
  }

  @Override
  public void updateBook(UpdateBookRequest request) {
    bookDomainService.updateBook(null);
  }

  @Override
  public void deleteBook(Long id) {
    BookId bookId = new BookId(id);
    bookDomainService.deleteBook(bookId);
  }

  @Override
  public BookResponse getBookById(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getBookById'");
  }

  @Override
  public PageList<BookResponse> getBooks(int pageNum, int pageSize) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getBooks'");
  }

  @Override
  public List<BookResponse> getAllBooks() {
    // TODO Auto-generated method stub

    throw new UnsupportedOperationException("Unimplemented method 'getAllBooks'");
  }

}
