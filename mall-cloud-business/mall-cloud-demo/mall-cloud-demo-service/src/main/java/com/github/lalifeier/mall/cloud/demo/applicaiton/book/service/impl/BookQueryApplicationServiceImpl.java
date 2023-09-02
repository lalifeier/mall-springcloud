package com.github.lalifeier.mall.cloud.demo.applicaiton.book.service.impl;

import com.github.lalifeier.mall.cloud.common.model.query.Pagination;
import com.github.lalifeier.mall.cloud.common.model.result.PageResult;
import com.github.lalifeier.mall.cloud.demo.api.book.model.dto.BookDTO;
import com.github.lalifeier.mall.cloud.demo.api.book.model.query.BookPageQuery;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.assembler.BookAssembler;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.service.BookQueryApplicationService;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.service.repository.BookQueryRepository;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.entity.Book;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.valueobject.BookId;
import com.github.lalifeier.mall.cloud.demo.domain.book.service.BookDomainService;
import com.github.lalifeier.mall.cloud.demo.infrastructure.persistence.mybatis.book.mapper.BookMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookQueryApplicationServiceImpl implements BookQueryApplicationService {
  private final BookDomainService bookDomainService;

  private final BookQueryRepository bookQueryRepository;

  private final BookMapper bookMapper;

  private final BookAssembler bookAssembler = BookAssembler.INSTANCE;

  @Override
  public BookDTO get(Long id) {
    BookId bookId = new BookId(id);
    Book book = bookDomainService.get(bookId);
    return this.bookAssembler.toDTO(book);
  }

  @Override
  public PageResult<BookDTO> query(BookPageQuery query) {
    Pagination<Book> bookPagination = bookQueryRepository.query(query);

    List<BookDTO> bookDTOList = bookAssembler.toDTO(bookPagination.getData());

    return PageResult.success(bookDTOList, bookPagination.getPageInfo());
  }
}
