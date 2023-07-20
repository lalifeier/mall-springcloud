package com.github.lalifeier.mall.cloud.demo.applicaiton.book.model.command;

import com.github.lalifeier.mall.cloud.common.model.marker.Command;
import lombok.Data;

@Data
public class BaseBookCommand implements Command {
  private String name;
}
