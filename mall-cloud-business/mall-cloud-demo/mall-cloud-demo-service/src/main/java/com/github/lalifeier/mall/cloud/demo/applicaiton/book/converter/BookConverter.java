package com.github.lalifeier.mall.cloud.demo.applicaiton.book.converter;

import com.github.lalifeier.mall.cloud.common.model.PageList;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.dto.BookDTO;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.dto.CreateBookCommand;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.dto.UpdateBookCommand;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.entity.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookConverter {
  BookConverter INSTANCE = Mappers.getMapper(BookConverter.class);

  @Mapping(source = "id", target = "id.value")
  BookEntity toDO(BookDTO bookDTO);

  @Mapping(target = "id", ignore = true)
  BookEntity toDO(CreateBookCommand createBookBO);

  @Mapping(source = "id", target = "id.value")
  BookEntity toDO(UpdateBookCommand UpdateBookCommand);

  @Mapping(source = "id.value", target = "id")
  BookDTO toDTO(BookEntity bookEntity);

  List<BookDTO> toDTO(List<BookEntity> bookPOList);

  PageList<BookDTO> toDTO(PageList<BookEntity> bookPOList);
}
