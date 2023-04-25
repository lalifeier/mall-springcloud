package com.github.lalifeier.mall.demo.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lalifeier.mall.common.model.PageInfo;
import com.github.lalifeier.mall.common.model.PageList;
import com.github.lalifeier.mall.demo.domain.book.model.entity.Book;
import com.github.lalifeier.mall.demo.domain.book.model.valueobject.BookId;
import com.github.lalifeier.mall.demo.domain.book.repository.BookRepository;
import com.github.lalifeier.mall.demo.infrastructure.repository.mybatis.book.converter.BookConverter;
import com.github.lalifeier.mall.demo.infrastructure.repository.mybatis.book.mapper.BookMapper;
import com.github.lalifeier.mall.demo.infrastructure.repository.mybatis.book.po.BookPO;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {
  @Resource
  private BookMapper bookMapper;

  private final BookConverter converter;

  public BookRepositoryImpl() {
    this.converter = BookConverter.INSTANCE;
  }

  @Override
  public void attach(@NotNull Book aggregate) {

  }

  @Override
  public void detach(@NotNull Book aggregate) {

  }

  @Override
  public Book find(@NotNull BookId bookId) {
    BookPO bookPO = bookMapper.selectById(bookId.getValue());
    return converter.convert(bookPO);
  }

  @Override
  public void remove(@NotNull Book aggregate) {
    //bookMapper.deleteById(aggregate.getId().getValue());
    BookPO accountUserPO = converter.convert(aggregate);
    bookMapper.deleteById(accountUserPO);
  }

  @Override
  public void save(@NotNull Book aggregate) {
    BookPO bookPO = converter.convert(aggregate);

    if (aggregate.getId() != null && aggregate.getId().getValue() > 0) {
      bookMapper.updateById(bookPO);
    } else {
      bookMapper.insert(bookPO);

      aggregate.setId(new BookId(bookPO.getId()));
    }
  }

  @Override
  public List<Book> list() {
    LambdaQueryWrapper<BookPO> queryWrapper = Wrappers.lambdaQuery();

    //queryWrapper.eq(BookPO::getId, "");
    queryWrapper.orderByDesc(BookPO::getId);

    List<BookPO> bookPOList = bookMapper.selectList(queryWrapper);

    List<Book> bookList = new ArrayList<>();
    converter.convertList(bookPOList, bookList);

    return  bookList;
  }

  public PageList<Book> page(Integer pageNum, Integer pageSize) {
    Page<BookPO> page = new Page<>(pageNum, pageSize);
    LambdaQueryWrapper<BookPO> queryWrapper = Wrappers.lambdaQuery();

    queryWrapper.orderByDesc(BookPO::getId);

    IPage<BookPO> bookPage  = bookMapper.selectPage(page, queryWrapper);

    List<BookPO> bookPOList = bookPage.getRecords();
    List<Book> bookList = new ArrayList<>();
    converter.convertList(bookPOList, bookList);

    PageList<Book> pageList= new PageList<>();

    pageList.setData(bookList);
    pageList.setPageInfo(bookPage);

    return null;
  }
}
