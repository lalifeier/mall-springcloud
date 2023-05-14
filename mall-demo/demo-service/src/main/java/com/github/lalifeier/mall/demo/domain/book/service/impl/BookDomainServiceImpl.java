package com.github.lalifeier.mall.demo.domain.book.service.impl;

import com.github.lalifeier.mall.demo.domain.book.model.entity.BookDO;
import com.github.lalifeier.mall.demo.domain.book.model.valueobject.BookId;
import com.github.lalifeier.mall.demo.domain.book.repository.BookRepository;
import com.github.lalifeier.mall.demo.domain.book.service.BookDomainService;
import org.springframework.stereotype.Service;

@Service
public class BookDomainServiceImpl implements BookDomainService {

  private final BookRepository bookRepository;

  public BookDomainServiceImpl(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Override
  public void createBook(BookDO bookDO) {
    bookRepository.save(bookDO);
  }

  @Override
  public void updateBook(BookDO bookDO) {
    bookRepository.save(bookDO);
  }

  @Override
  public BookDO getBookById(BookId bookId) {
    return bookRepository.find(bookId);
  }

  @Override
  public void deleteBook(BookId bookId) {
    BookDO bookDO = bookRepository.find(bookId);
    if (bookDO != null) {
      bookRepository.remove(bookDO);
    }

    // throw new BookNotFoundException(bookId);
  }

  //@Override
  //public List<BookDO> getAllBooks() {
  //  return  bookRepository.findAll();
  //}
  //
  //@Override
  //public PageList<BookDO> getBooks(int pageNum, int pageSize) {
  //  return bookRepository.page(pageNum, pageSize);
  //}

}
