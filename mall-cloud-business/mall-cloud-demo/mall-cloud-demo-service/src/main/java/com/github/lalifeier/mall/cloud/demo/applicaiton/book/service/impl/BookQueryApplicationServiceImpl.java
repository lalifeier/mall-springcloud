package com.github.lalifeier.mall.cloud.demo.applicaiton.book.service.impl;

import com.github.lalifeier.mall.cloud.common.model.query.AbstractPageQuery;
import com.github.lalifeier.mall.cloud.common.model.query.Pagination;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.converter.BookConverter;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.model.dto.BookDTO;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.service.BookQueryApplicationService;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.service.repository.BookQueryRepository;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.entity.Book;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.valueobject.BookId;
import com.github.lalifeier.mall.cloud.demo.domain.book.service.BookDomainService;
import com.github.lalifeier.mall.cloud.demo.infrastructure.persistence.mybatis.book.mapper.BookMapper;
import org.springframework.stereotype.Service;

@Service
public class BookQueryApplicationServiceImpl implements BookQueryApplicationService {
  private final BookDomainService bookDomainService;

  private final BookQueryRepository bookQueryRepository;

  private final BookMapper bookMapper;

  private final BookConverter bookConverter = BookConverter.INSTANCE;

  public BookQueryApplicationServiceImpl(BookDomainService bookDomainService, BookQueryRepository bookQueryRepository, BookMapper bookMapper) {
    this.bookDomainService = bookDomainService;
    this.bookQueryRepository = bookQueryRepository;
    this.bookMapper = bookMapper;
  }


  @Override
  public BookDTO get(Long id) {
    BookId bookId = new BookId(id);
    Book book = bookDomainService.get(bookId);
    return this.bookConverter.toDTO(book);
  }

  @Override
  public Pagination<BookDTO> query(AbstractPageQuery query) {
    Pagination<Book> bookDOPagination = bookQueryRepository.query(query);
    return this.bookConverter.toDTO(bookDOPagination);
  }


}
