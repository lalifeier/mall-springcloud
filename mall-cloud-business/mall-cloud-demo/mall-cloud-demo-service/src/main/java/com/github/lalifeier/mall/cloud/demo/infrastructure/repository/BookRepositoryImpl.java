package com.github.lalifeier.mall.cloud.demo.infrastructure.repository;

import com.github.lalifeier.mall.cloud.demo.domain.book.model.entity.BookEntity;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.valueobject.BookId;
import com.github.lalifeier.mall.cloud.demo.domain.book.repository.BookRepository;
import com.github.lalifeier.mall.cloud.demo.infrastructure.repository.mybatis.book.converter.BookConverter;
import com.github.lalifeier.mall.cloud.demo.infrastructure.repository.mybatis.book.mapper.BookMapper;
import com.github.lalifeier.mall.cloud.demo.infrastructure.repository.mybatis.book.po.BookPO;
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
  public void attach(@NotNull BookEntity aggregate) {

  }

  @Override
  public void detach(@NotNull BookEntity aggregate) {

  }

  @Override
  public BookEntity find(@NotNull BookId bookId) {
    Long id = bookId.getValue();
    BookPO bookPO = bookMapper.selectById(id);
    return bookConverter.convert(bookPO);
  }

  @Override
  public void remove(@NotNull BookEntity aggregate) {
    BookPO bookPO = bookConverter.convert(aggregate);
    bookMapper.deleteById(bookPO);
  }

  @Override
  public BookEntity save(@NotNull BookEntity aggregate) {
    BookPO bookPO = bookConverter.convert(aggregate);
    if (aggregate.getId() != null && aggregate.getId().getValue() > 0) {
      bookMapper.updateById(bookPO);
    } else {
      bookMapper.insert(bookPO);
      aggregate.setId(new BookId(bookPO.getId()));
    }
    return aggregate;
  }


}
