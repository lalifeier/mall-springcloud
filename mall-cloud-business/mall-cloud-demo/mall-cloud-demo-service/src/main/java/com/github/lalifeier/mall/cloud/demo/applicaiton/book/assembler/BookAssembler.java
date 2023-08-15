package com.github.lalifeier.mall.cloud.demo.applicaiton.book.assembler;

import com.github.lalifeier.mall.cloud.demo.api.book.model.command.CreateBookCommand;
import com.github.lalifeier.mall.cloud.demo.api.book.model.command.UpdateBookCommand;
import com.github.lalifeier.mall.cloud.demo.api.book.model.dto.BookDTO;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.entity.Book;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
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
