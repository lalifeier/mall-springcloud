package com.github.lalifeier.mall.cloud.demo.interfaces.rest.book.converter;

import com.github.lalifeier.mall.cloud.common.model.PageList;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.dto.BookDTO;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.dto.CreateBookCommand;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.dto.UpdateBookCommand;
import com.github.lalifeier.mall.cloud.demo.interfaces.rest.book.model.request.CreateBookRequest;
import com.github.lalifeier.mall.cloud.demo.interfaces.rest.book.model.request.UpdateBookRequest;
import com.github.lalifeier.mall.cloud.demo.interfaces.rest.book.model.response.BookResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookConverter {
  BookConverter INSTANCE = Mappers.getMapper(BookConverter.class);

  CreateBookCommand toDTO(CreateBookRequest createBookRequest);

  @Mapping(target = "id", ignore = true)
  UpdateBookCommand toDTO(UpdateBookRequest updateBookRequest);

  BookResponse toVO(BookDTO bookDTO);

  List<BookResponse> toVO(List<BookDTO> bookDTOList);

  PageList<BookResponse> toVO(PageList<BookDTO> bookPOList);
}
