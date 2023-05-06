package com.github.lalifeier.mall.demo.domain.book.repository;

import java.util.List;

import com.github.lalifeier.mall.common.model.PageList;
import com.github.lalifeier.mall.common.model.ddd.Repository;
import com.github.lalifeier.mall.demo.domain.book.model.entity.BookDO;
import com.github.lalifeier.mall.demo.domain.book.model.valueobject.BookId;

public interface BookRepository extends Repository<BookDO, BookId> {
  List<BookDO> findAll();

  PageList<BookDO> page(Integer pageNum, Integer pageSize);
}
