package com.github.lalifeier.mall.demo.domain.book.model.entity;

import com.github.lalifeier.mall.common.model.ddd.Aggregate;
import com.github.lalifeier.mall.demo.domain.book.model.valueobject.BookId;

import lombok.Data;

@Data
public class BookDO implements Aggregate<BookId> {
  private BookId id;
}
