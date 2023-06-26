package com.github.lalifeier.mall.cloud.demo.applicaiton.book.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateBookCommand {
  private Long id;
  private String name;
}
