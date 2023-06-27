package com.github.lalifeier.mall.cloud.demo.applicaiton.book.service.impl;

import com.github.lalifeier.mall.cloud.demo.applicaiton.book.converter.BookConverter;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.model.command.CreateBookCommand;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.model.command.UpdateBookCommand;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.service.BookApplicationService;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.entity.BookEntity;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.valueobject.BookId;
import com.github.lalifeier.mall.cloud.demo.domain.book.repository.BookRepository;
import com.github.lalifeier.mall.cloud.demo.domain.book.service.BookDomainService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookApplicationServiceImpl implements BookApplicationService {
  private final BookDomainService bookDomainService;

  private final BookRepository bookRepository;

  private final BookConverter bookConverter = BookConverter.INSTANCE;

  public BookApplicationServiceImpl(BookDomainService bookDomainService, BookRepository bookRepository) {
    this.bookDomainService = bookDomainService;
    this.bookRepository = bookRepository;
  }

  @Override
  public void create(CreateBookCommand command) {
    BookEntity bookEntity = this.bookConverter.toDO(command);
    bookDomainService.create(bookEntity);
  }

  @Override
  public void update(UpdateBookCommand command) {
    BookEntity bookEntity = this.bookConverter.toDO(command);
    bookDomainService.update(bookEntity);
  }

  @Override
  public void delete(Long id) {
    BookId bookId = new BookId(id);
    bookDomainService.delete(bookId);
  }
}
