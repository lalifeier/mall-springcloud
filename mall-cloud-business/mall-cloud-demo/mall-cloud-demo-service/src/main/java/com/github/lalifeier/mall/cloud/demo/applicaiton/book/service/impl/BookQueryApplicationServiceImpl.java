package com.github.lalifeier.mall.cloud.demo.applicaiton.book.service.impl;

import com.github.lalifeier.mall.cloud.common.model.query.Pagination;
import com.github.lalifeier.mall.cloud.common.model.query.PageQuery;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.converter.BookConverter;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.model.dto.BookDTO;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.service.BookQueryApplicationService;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.entity.BookEntity;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.valueobject.BookId;
import com.github.lalifeier.mall.cloud.demo.domain.book.repository.BookRepository;
import com.github.lalifeier.mall.cloud.demo.domain.book.service.BookDomainService;
import com.github.lalifeier.mall.cloud.demo.infrastructure.repository.mybatis.book.mapper.BookMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookQueryApplicationServiceImpl implements BookQueryApplicationService {
  private final BookDomainService bookDomainService;

  private final BookRepository bookRepository;

  private final BookMapper bookMapper;

  private final BookConverter bookConverter = BookConverter.INSTANCE;

  public BookQueryApplicationServiceImpl(BookDomainService bookDomainService, BookRepository bookRepository, BookMapper bookMapper) {
    this.bookDomainService = bookDomainService;
    this.bookRepository = bookRepository;
    this.bookMapper = bookMapper;
  }


  @Override
  public BookDTO getBookById(Long id) {
    BookId bookId = new BookId(id);
    BookEntity bookEntity = bookDomainService.getBookById(bookId);
    return this.bookConverter.toDTO(bookEntity);
  }

  @Override
  public Pagination<BookDTO> getBooks(PageQuery query) {
    Pagination<BookEntity> bookDOPagination = bookRepository.pageList(query);
    return this.bookConverter.toDTO(bookDOPagination);

//    Page<BookPO> page = new Page<>(query.getPageNum(), query.getPageSize());
//
//    QueryWrapper<BookPO> queryWrapper = QueryWrapperUtils.buildQueryWrapper(BookPO.class, query);
//
//    IPage<BookPO> bookPage = bookMapper.selectPage(page, queryWrapper);
//
//    return PageConverter.convert(bookPage, bookConverter::convert);
  }

  @Override
  public List<BookDTO> getAllBooks() {
    //List<BookDO> bookDOList = bookDomainService.getAllBooks();
    //return this.bookConverter.toDTO(bookDOList);
    return null;
  }
}
