package com.github.lalifeier.mall.demo.presentation.rest.book.dto.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CreateBookRequest {
  private String title;
  private String author;
  private BigDecimal price;
}
