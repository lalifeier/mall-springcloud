package com.github.lalifeier.mall.cloud.demo.applicaiton.book.service.impl;

import com.github.lalifeier.mall.cloud.common.model.PageList;
import com.github.lalifeier.mall.cloud.common.model.PageRequest;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.bo.BookBO;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.bo.CreateBookBO;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.bo.UpdateBookBO;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.converter.BookConverter;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.service.BookApplicationService;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.entity.BookDO;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.valueobject.BookId;
import com.github.lalifeier.mall.cloud.demo.domain.book.repository.BookRepository;
import com.github.lalifeier.mall.cloud.demo.domain.book.service.BookDomainService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
  public void createBook(CreateBookBO createBookBO) {
    BookDO bookDO = this.bookConverter.toDO(createBookBO);
    bookDomainService.createBook(bookDO);
  }

  @Override
  public void updateBook(UpdateBookBO UpdateBookBO) {
    BookDO bookDO = this.bookConverter.toDO(UpdateBookBO);
    bookDomainService.updateBook(bookDO);
  }

  @Override
  public void deleteBook(Long id) {
    BookId bookId = new BookId(id);
    bookDomainService.deleteBook(bookId);
  }

  @Override
  public BookBO getBookById(Long id) {
    BookId bookId = new BookId(id);
    BookDO bookDO = bookDomainService.getBookById(bookId);
    return this.bookConverter.toDTO(bookDO);
  }

  @Override
  public PageList<BookBO> getBooks(PageRequest request) {
    PageList<BookDO> bookDOPageList = bookRepository.pageList(request);
    return this.bookConverter.toDTO(bookDOPageList);
  }
  
  @Override
  public List<BookBO> getAllBooks() {
    //List<BookDO> bookDOList = bookDomainService.getAllBooks();
    //return this.bookConverter.toDTO(bookDOList);
    return null;
  }

}
