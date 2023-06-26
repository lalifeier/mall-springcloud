package com.github.lalifeier.mall.cloud.demo.infrastructure.repository.mybatis.book.converter;

import com.github.lalifeier.mall.cloud.demo.domain.book.model.entity.BookEntity;
import com.github.lalifeier.mall.cloud.demo.infrastructure.repository.mybatis.book.po.BookPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookConverter {
  BookConverter INSTANCE = Mappers.getMapper(BookConverter.class);

  @Mapping(source = "id", target = "id.value")
  BookEntity convert(BookPO bookPO);

  //@Mapping(target = "id", ignore = true)
  @Mapping(source = "id.value", target = "id")
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "createdBy", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "updatedBy", ignore = true)
  @Mapping(target = "isDeleted", ignore = true)
  BookPO convert(BookEntity bookEntity);

  List<BookEntity> convertList(List<BookPO> bookPOList);
}
