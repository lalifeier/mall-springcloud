package com.github.lalifeier.mall.cloud.demo.applicaiton.book.assembler;

import com.github.lalifeier.mall.cloud.demo.book.dto.BookDTO;
import com.github.lalifeier.mall.cloud.demo.book.dto.command.CreateBookCommand;
import com.github.lalifeier.mall.cloud.demo.book.dto.command.UpdateBookCommand;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookAssembler {
  BookAssembler INSTANCE = Mappers.getMapper(BookAssembler.class);

  @Mapping(target = "id", ignore = true)
  Book toEntity(CreateBookCommand createBookBO);

  @Mapping(source = "id", target = "id.value")
  Book toEntity(UpdateBookCommand UpdateBookCommand);

  @Mapping(source = "id.value", target = "id")
  BookDTO toDTO(Book book);

  List<BookDTO> toDTO(List<Book> bookList);
}
