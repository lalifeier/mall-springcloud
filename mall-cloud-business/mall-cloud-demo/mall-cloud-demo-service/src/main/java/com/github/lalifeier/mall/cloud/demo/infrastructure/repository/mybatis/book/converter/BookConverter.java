package com.github.lalifeier.mall.cloud.demo.infrastructure.repository.mybatis.book.converter;

import java.util.List;

import com.github.lalifeier.mall.cloud.demo.domain.book.model.entity.BookDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.github.lalifeier.mall.cloud.demo.infrastructure.repository.mybatis.book.po.BookPO;

@Mapper
public interface BookConverter {
  BookConverter INSTANCE = Mappers.getMapper(BookConverter.class);

  @Mapping(source = "id", target = "id.value")
  BookDO convert(BookPO bookPO);

  //@Mapping(target = "id", ignore = true)
  @Mapping(source = "id.value", target = "id")
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "createdBy", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "updatedBy", ignore = true)
  @Mapping(target = "isDeleted", ignore = true)
  BookPO convert(BookDO bookDO);

  List<BookDO> convertList(List<BookPO> bookPOList);
}
