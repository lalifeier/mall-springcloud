package com.github.lalifeier.mall.demo.domain.book.repository;

import com.github.lalifeier.mall.common.model.Repository;
import com.github.lalifeier.mall.demo.domain.book.model.entity.Book;
import com.github.lalifeier.mall.demo.domain.book.model.valueobject.BookId;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface BookRepository extends Repository<Book, BookId> {
  List<Book> list();
  //pageList();
}
