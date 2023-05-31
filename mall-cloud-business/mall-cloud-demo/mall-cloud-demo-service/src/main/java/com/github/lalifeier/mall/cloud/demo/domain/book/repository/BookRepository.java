package com.github.lalifeier.mall.cloud.demo.domain.book.repository;

import com.github.lalifeier.mall.cloud.common.model.PageList;
import com.github.lalifeier.mall.cloud.common.model.PageRequest;
import com.github.lalifeier.mall.cloud.common.model.ddd.Repository;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.entity.BookDO;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.valueobject.BookId;

import java.util.List;

public interface BookRepository extends Repository<BookDO, BookId> {
  List<BookDO> findAll();

  PageList<BookDO> page(PageRequest request);
}
