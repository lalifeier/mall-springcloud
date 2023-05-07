package com.github.lalifeier.mall.demo.applicaiton.book.converter;

import com.github.lalifeier.mall.common.model.PageList;
import com.github.lalifeier.mall.demo.applicaiton.book.bo.BookBO;
import com.github.lalifeier.mall.demo.applicaiton.book.bo.CreateBookBO;
import com.github.lalifeier.mall.demo.applicaiton.book.bo.UpdateBookBO;
import com.github.lalifeier.mall.demo.domain.book.model.entity.BookDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookConverter {
  BookConverter INSTANCE = Mappers.getMapper(BookConverter.class);

  @Mapping(source = "id", target = "id.value")
  BookDO toDO(BookBO bookBO);

  @Mapping(target = "id", ignore = true)
  BookDO toDO(CreateBookBO createBookBO);

  @Mapping(source = "id", target = "id.value")
  BookDO toDO(UpdateBookBO UpdateBookBO);

  @Mapping(source = "id.value", target = "id")
  BookBO toDTO(BookDO bookDO);

  List<BookBO> toDTO(List<BookDO> bookPOList);

  PageList<BookBO> toDTO(PageList<BookDO> bookPOList);
}
