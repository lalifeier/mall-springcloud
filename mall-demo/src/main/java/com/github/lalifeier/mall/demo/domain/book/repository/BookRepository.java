package com.github.lalifeier.mall.demo.domain.book.repository;

import com.github.lalifeier.mall.common.model.ddd.Repository;
import com.github.lalifeier.mall.demo.domain.book.model.entity.BookDO;
import com.github.lalifeier.mall.demo.domain.book.model.valueobject.BookId;

import java.util.List;

public interface BookRepository extends Repository<BookDO, BookId> {
  List<BookDO> list();
  //pageList();
}
