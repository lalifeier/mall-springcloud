package com.github.lalifeier.mall.cloud.demo.applicaiton.book.command;

import com.github.lalifeier.mall.cloud.demo.applicaiton.book.dto.CreateBookCommand;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.dto.UpdateBookCommand;

public interface BookCommandApplicationService {
  void createBook(CreateBookCommand createBookCommand);

  void updateBook(UpdateBookCommand updateBookCommand);

  void deleteBook(Long id);
}
