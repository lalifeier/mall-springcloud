package com.github.lalifeier.mall.cloud.demo.domain.book.service.impl;

import com.github.lalifeier.mall.cloud.demo.domain.book.model.entity.Book;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.valueobject.BookId;
import com.github.lalifeier.mall.cloud.demo.domain.book.repository.BookRepository;
import com.github.lalifeier.mall.cloud.demo.domain.book.service.BookDomainService;
import org.springframework.stereotype.Service;

@Service
public class BookDomainServiceImpl implements BookDomainService {

  private final BookRepository bookRepository;

  public BookDomainServiceImpl(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Override
  public Book create(Book book) {
    return bookRepository.save(book);
  }

  @Override
  public Book update(Book book) {
    return bookRepository.save(book);
  }

  @Override
  public Book get(BookId bookId) {
    return bookRepository.find(bookId);
  }

  @Override
  public void delete(BookId bookId) {
    Book book = bookRepository.find(bookId);
    if (book != null) {
      bookRepository.remove(book);
    }
  }
}
