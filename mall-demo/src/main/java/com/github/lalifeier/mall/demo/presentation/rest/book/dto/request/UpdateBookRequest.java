package com.github.lalifeier.mall.demo.presentation.rest.book.dto.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class UpdateBookRequest {
  private Long id;
  private String title;
  private String author;
  private BigDecimal price;
}
