package com.github.lalifeier.mall.cloud.demo.domain.book.model.entity;

import com.github.lalifeier.mall.cloud.common.model.ddd.Aggregate;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.valueobject.BookId;
import lombok.Data;

@Data
public class BookEntity implements Aggregate<BookId> {
  private BookId id;
  private String name;
}
