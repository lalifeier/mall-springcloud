package com.github.lalifeier.mall.cloud.demo.applicaiton.book.service;

import com.github.lalifeier.mall.cloud.common.model.ddd.ApplicationService;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.model.command.CreateBookCommand;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.model.command.UpdateBookCommand;

public interface BookApplicationService  extends ApplicationService {
  void create(CreateBookCommand command);

  void update(UpdateBookCommand command);

  void delete(Long id);
}
