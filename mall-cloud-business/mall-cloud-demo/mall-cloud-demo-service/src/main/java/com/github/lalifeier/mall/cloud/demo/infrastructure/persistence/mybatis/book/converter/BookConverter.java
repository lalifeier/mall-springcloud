package com.github.lalifeier.mall.cloud.demo.infrastructure.persistence.mybatis.book.converter;

import com.github.lalifeier.mall.cloud.demo.domain.book.model.entity.Book;
import com.github.lalifeier.mall.cloud.demo.infrastructure.persistence.mybatis.book.po.BookPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookConverter {
  BookConverter INSTANCE = Mappers.getMapper(BookConverter.class);

  @Mapping(source = "id", target = "id.value")
  Book fromData(BookPO bookPO);

  // @Mapping(target = "id", ignore = true)
  @Mapping(source = "id.value", target = "id")
  @Mapping(target = "createdTime", ignore = true)
  @Mapping(target = "createdBy", ignore = true)
  @Mapping(target = "updatedTime", ignore = true)
  @Mapping(target = "updatedBy", ignore = true)
  @Mapping(target = "deleted", ignore = true)
  BookPO toData(Book book);
}
