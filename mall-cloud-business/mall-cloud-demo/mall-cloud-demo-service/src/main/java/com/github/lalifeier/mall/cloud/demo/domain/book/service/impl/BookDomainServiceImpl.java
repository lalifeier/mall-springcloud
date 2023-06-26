package com.github.lalifeier.mall.cloud.demo.domain.book.service.impl;

import com.github.lalifeier.mall.cloud.demo.domain.book.model.entity.BookEntity;
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
  public void createBook(BookEntity bookEntity) {
    bookRepository.save(bookEntity);
  }

  @Override
  public void updateBook(BookEntity bookEntity) {
    bookRepository.save(bookEntity);
  }

  @Override
  public BookEntity getBookById(BookId bookId) {
    return bookRepository.find(bookId);
  }

  @Override
  public void deleteBook(BookId bookId) {
    BookEntity bookEntity = bookRepository.find(bookId);
    if (bookEntity != null) {
      bookRepository.remove(bookEntity);
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
