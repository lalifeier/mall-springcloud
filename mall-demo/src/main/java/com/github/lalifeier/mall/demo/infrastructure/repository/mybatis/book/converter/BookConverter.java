package com.github.lalifeier.mall.demo.infrastructure.repository.mybatis.book.converter;

import com.github.lalifeier.mall.demo.domain.book.model.entity.Book;
import com.github.lalifeier.mall.demo.infrastructure.repository.mybatis.book.po.BookPO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookConverter {
  BookConverter INSTANCE = Mappers.getMapper(BookConverter.class);

  Book convert(BookPO bookPO);

  BookPO convert(Book aggregate);

  void convertList(List<BookPO> sourceList, @MappingTarget List<Book> targetList);
}
