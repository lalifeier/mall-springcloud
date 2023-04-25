package com.github.lalifeier.mall.demo.domain.book.model.entity;

import com.github.lalifeier.mall.common.model.Aggregate;
import com.github.lalifeier.mall.demo.domain.book.model.valueobject.BookId;
import lombok.Data;

@Data
public class Book implements Aggregate<BookId> {
  private BookId id;
}
