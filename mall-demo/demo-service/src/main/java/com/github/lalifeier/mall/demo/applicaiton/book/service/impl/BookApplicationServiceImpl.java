package com.github.lalifeier.mall.demo.applicaiton.book.service.impl;

import java.util.List;

import com.github.lalifeier.mall.demo.applicaiton.book.bo.BookBO;
import com.github.lalifeier.mall.demo.applicaiton.book.bo.CreateBookBO;
import com.github.lalifeier.mall.demo.applicaiton.book.bo.UpdateBookBO;
import com.github.lalifeier.mall.demo.applicaiton.book.converter.BookConverter;
import com.github.lalifeier.mall.demo.domain.book.model.entity.BookDO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.lalifeier.mall.common.model.PageList;
import com.github.lalifeier.mall.demo.applicaiton.book.service.BookApplicationService;
import com.github.lalifeier.mall.demo.domain.book.model.valueobject.BookId;
import com.github.lalifeier.mall.demo.domain.book.service.BookDomainService;

@Service
@Transactional
public class BookApplicationServiceImpl implements BookApplicationService {

  private final BookDomainService bookDomainService;

  private final BookConverter bookConverter =  BookConverter.INSTANCE;

  public BookApplicationServiceImpl(BookDomainService bookDomainService) {
    this.bookDomainService = bookDomainService;
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
  public PageList<BookBO> getBooks(int pageNum, int pageSize) {
    PageList<BookDO> bookDOPageList =  bookDomainService.getBooks(pageNum, pageSize);
    return this.bookConverter.toDTO(bookDOPageList);
  }

  @Override
  public List<BookBO> getAllBooks() {
    List<BookDO> bookDOList = bookDomainService.getAllBooks();
    return this.bookConverter.toDTO(bookDOList);
  }

}
