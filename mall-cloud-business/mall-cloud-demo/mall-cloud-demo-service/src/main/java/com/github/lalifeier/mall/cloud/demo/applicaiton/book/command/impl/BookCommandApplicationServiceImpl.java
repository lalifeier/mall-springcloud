package com.github.lalifeier.mall.cloud.demo.applicaiton.book.command.impl;

import com.github.lalifeier.mall.cloud.demo.applicaiton.book.command.BookCommandApplicationService;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.converter.BookConverter;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.dto.CreateBookCommand;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.dto.UpdateBookCommand;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.entity.BookEntity;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.valueobject.BookId;
import com.github.lalifeier.mall.cloud.demo.domain.book.repository.BookRepository;
import com.github.lalifeier.mall.cloud.demo.domain.book.service.BookDomainService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookCommandApplicationServiceImpl implements BookCommandApplicationService {
  private final BookDomainService bookDomainService;

  private final BookRepository bookRepository;

  private final BookConverter bookConverter = BookConverter.INSTANCE;

  public BookCommandApplicationServiceImpl(BookDomainService bookDomainService, BookRepository bookRepository) {
    this.bookDomainService = bookDomainService;
    this.bookRepository = bookRepository;
  }

  @Override
  public void createBook(CreateBookCommand createBookCommand) {
    BookEntity bookEntity = this.bookConverter.toDO(createBookCommand);
    bookDomainService.createBook(bookEntity);
  }

  @Override
  public void updateBook(UpdateBookCommand UpdateBookCommand) {
    BookEntity bookEntity = this.bookConverter.toDO(UpdateBookCommand);
    bookDomainService.updateBook(bookEntity);
  }

  @Override
  public void deleteBook(Long id) {
    BookId bookId = new BookId(id);
    bookDomainService.deleteBook(bookId);
  }
}
