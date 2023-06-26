package com.github.lalifeier.mall.cloud.demo.infrastructure.repository;

import com.github.lalifeier.mall.cloud.demo.applicaiton.book.service.repository.BookQueryRepository;
import com.github.lalifeier.mall.cloud.demo.infrastructure.repository.mybatis.book.converter.BookConverter;
import com.github.lalifeier.mall.cloud.demo.infrastructure.repository.mybatis.book.mapper.BookMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class BookQueryRepositoryImpl implements BookQueryRepository {
  private final BookMapper bookMapper;

  private final BookConverter bookConverter = BookConverter.INSTANCE;

  public BookQueryRepositoryImpl(BookMapper bookMapper) {
    this.bookMapper = bookMapper;
  }
}
