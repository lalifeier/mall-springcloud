package com.github.lalifeier.mall.cloud.demo.api.book.model.command;

import com.github.lalifeier.mall.cloud.common.model.marker.Command;
import lombok.Data;

@Data
public class UpdateBookCommand implements Command {
  private Long id;

  private String name;
}
