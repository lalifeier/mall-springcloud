package com.github.lalifeier.mall.demo.presentation.rest.book.dto.request;

import lombok.Data;

@Data
public class PageListBookRequest {
  int pageNum;
  int pageSize;
}
