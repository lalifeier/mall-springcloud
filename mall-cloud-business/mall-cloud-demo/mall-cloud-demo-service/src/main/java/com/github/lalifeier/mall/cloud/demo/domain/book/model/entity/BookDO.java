package com.github.lalifeier.mall.cloud.demo.domain.book.model.entity;

import com.github.lalifeier.mall.cloud.demo.domain.book.model.valueobject.BookId;
import com.github.lalifeier.mall.cloud.common.model.ddd.Aggregate;

import lombok.Data;

@Data
public class BookDO implements Aggregate<BookId> {
  private BookId id;
  private String name;
}
