package com.github.lalifeier.mall.demo.infrastructure.repository.mybatis.book.converter;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.github.lalifeier.mall.demo.domain.book.model.entity.BookDO;
import com.github.lalifeier.mall.demo.infrastructure.repository.mybatis.book.po.BookPO;

@Mapper
public interface BookConverter {
  BookConverter INSTANCE = Mappers.getMapper(BookConverter.class);

  @Mapping(source = "id", target = "id.value")
  BookDO convert(BookPO bookPO);

  @Mapping(target = "id", ignore = true)
  BookPO convert(BookDO bookDO);

  // @IterableMapping(qualifiedByName = "convert")
  List<BookDO> convertList(List<BookPO> bookPOList);
}
