package com.github.lalifeier.mall.cloud.demo.infrastructure.persistence.repository;

import com.github.lalifeier.mall.cloud.demo.domain.book.model.entity.Book;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.valueobject.BookId;
import com.github.lalifeier.mall.cloud.demo.domain.book.repository.BookRepository;
import com.github.lalifeier.mall.cloud.demo.infrastructure.persistence.mybatis.book.converter.BookConverter;
import com.github.lalifeier.mall.cloud.demo.infrastructure.persistence.mybatis.book.mapper.BookMapper;
import com.github.lalifeier.mall.cloud.demo.infrastructure.persistence.mybatis.book.po.BookPO;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class BookRepositoryImpl implements BookRepository {

  private final BookMapper bookMapper;

  private final BookConverter bookConverter = BookConverter.INSTANCE;

  public BookRepositoryImpl(BookMapper bookMapper) {
    this.bookMapper = bookMapper;
  }

  @Override
  public void attach(@NotNull Book aggregate) {

  }

  @Override
  public void detach(@NotNull Book aggregate) {

  }

  @Override
  public Book find(@NotNull BookId bookId) {
    Long id = bookId.getValue();
    BookPO bookPO = bookMapper.selectById(id);
    return bookConverter.fromData(bookPO);
  }

  @Override
  public void remove(@NotNull Book aggregate) {
    BookPO bookPO = bookConverter.toData(aggregate);
    bookMapper.deleteById(bookPO);
  }

  @Override
  public void save(@NotNull Book aggregate) {
    BookPO bookPO = bookConverter.toData(aggregate);
    if (aggregate.getId() != null && aggregate.getId().getValue() > 0) {
      bookMapper.updateById(bookPO);
    } else {
      bookMapper.insert(bookPO);
      aggregate.setId(bookConverter.fromData(bookPO).getId());
    }
  }
}
