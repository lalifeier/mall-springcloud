package com.github.lalifeier.mall.cloud.demo.interfaces.rest.book.converter;

import com.github.lalifeier.mall.cloud.common.model.query.Pagination;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.model.command.CreateBookCommand;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.model.command.UpdateBookCommand;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.model.dto.BookDTO;
import com.github.lalifeier.mall.cloud.demo.interfaces.rest.book.model.request.CreateBookRequest;
import com.github.lalifeier.mall.cloud.demo.interfaces.rest.book.model.request.UpdateBookRequest;
import com.github.lalifeier.mall.cloud.demo.interfaces.rest.book.model.response.BookResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookConverter {
  BookConverter INSTANCE = Mappers.getMapper(BookConverter.class);

  CreateBookCommand toDTO(CreateBookRequest createBookRequest);

  @Mapping(target = "id", ignore = true)
  UpdateBookCommand toDTO(UpdateBookRequest updateBookRequest);

  BookResponse toVO(BookDTO bookDTO);

  List<BookResponse> toVO(List<BookDTO> bookDTOList);

  Pagination<BookResponse> toVO(Pagination<BookDTO> bookDTOPagination);
}
