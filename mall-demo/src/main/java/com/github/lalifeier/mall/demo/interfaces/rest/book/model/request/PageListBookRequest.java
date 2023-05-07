package com.github.lalifeier.mall.demo.interfaces.rest.book.model.request;

import lombok.Data;

@Data
public class PageListBookRequest {
  int pageNum;
  int pageSize;
}
