package com.github.lalifeier.mall.demo.interfaces.rest.book.converter;

import com.github.lalifeier.mall.common.model.PageList;
import com.github.lalifeier.mall.demo.applicaiton.book.bo.BookBO;
import com.github.lalifeier.mall.demo.applicaiton.book.bo.CreateBookBO;
import com.github.lalifeier.mall.demo.applicaiton.book.bo.UpdateBookBO;
import com.github.lalifeier.mall.demo.interfaces.rest.book.model.request.CreateBookRequest;
import com.github.lalifeier.mall.demo.interfaces.rest.book.model.response.BookResponse;
import com.github.lalifeier.mall.demo.interfaces.rest.book.model.request.UpdateBookRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookConverter {
  BookConverter INSTANCE = Mappers.getMapper(BookConverter.class);

  CreateBookBO toDTO(CreateBookRequest createBookRequest);

  UpdateBookBO toDTO(UpdateBookRequest updateBookRequest);

  BookResponse toVO(BookBO bookBO);

  List<BookResponse> toVO(List<BookBO> bookBOList);

  PageList<BookResponse> toVO(PageList<BookBO> bookPOList);
}
