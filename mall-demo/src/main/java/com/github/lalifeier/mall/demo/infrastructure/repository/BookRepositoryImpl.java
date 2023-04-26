package com.github.lalifeier.mall.demo.infrastructure.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.jetbrains.annotations.NotNull;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lalifeier.mall.common.model.PageList;
import com.github.lalifeier.mall.demo.domain.book.model.entity.BookDO;
import com.github.lalifeier.mall.demo.domain.book.model.valueobject.BookId;
import com.github.lalifeier.mall.demo.domain.book.repository.BookRepository;
import com.github.lalifeier.mall.demo.infrastructure.repository.mybatis.book.converter.BookConverter;
import com.github.lalifeier.mall.demo.infrastructure.repository.mybatis.book.mapper.BookMapper;
import com.github.lalifeier.mall.demo.infrastructure.repository.mybatis.book.po.BookPO;

public class BookRepositoryImpl implements BookRepository {
  @Resource
  private BookMapper bookMapper;

  private final BookConverter bookConverter;

  public BookRepositoryImpl() {
    this.bookConverter = BookConverter.INSTANCE;
  }

  @Override
  public void attach(@NotNull BookDO aggregate) {

  }

  @Override
  public void detach(@NotNull BookDO aggregate) {

  }

  @Override
  public BookDO find(@NotNull BookId bookId) {
    BookPO bookPO = bookMapper.selectById(bookId.getValue());
    return bookConverter.toBookDO(bookPO);
  }

  @Override
  public void remove(@NotNull BookDO aggregate) {
    // bookMapper.deleteById(aggregate.getId().getValue());
    BookPO accountUserPO = bookConverter.toBookPO(aggregate);
    bookMapper.deleteById(accountUserPO);
  }

  @Override
  public void save(@NotNull BookDO aggregate) {
    BookPO bookPO = bookConverter.toBookPO(aggregate);

    if (aggregate.getId() != null && aggregate.getId().getValue() > 0) {
      bookMapper.updateById(bookPO);
    } else {
      bookMapper.insert(bookPO);

      aggregate.setId(new BookId(bookPO.getId()));
    }
  }

  @Override
  public List<BookDO> list() {
    LambdaQueryWrapper<BookPO> queryWrapper = Wrappers.lambdaQuery();

    // queryWrapper.eq(BookPO::getId, "");
    queryWrapper.orderByDesc(BookPO::getId);

    List<BookPO> bookPOList = bookMapper.selectList(queryWrapper);

    List<BookDO> bookDOList = new ArrayList<>();
    bookConverter.convertList(bookPOList, bookDOList);

    return bookDOList;
  }

  public PageList<BookDO> page(Integer pageNum, Integer pageSize) {
    Page<BookPO> page = new Page<>(pageNum, pageSize);
    LambdaQueryWrapper<BookPO> queryWrapper = Wrappers.lambdaQuery();

    queryWrapper.orderByDesc(BookPO::getId);

    IPage<BookPO> bookPage = bookMapper.selectPage(page, queryWrapper);

    List<BookPO> bookPOList = bookPage.getRecords();
    List<BookDO> bookDOList = new ArrayList<>();
    bookConverter.convertList(bookPOList, bookDOList);

    //PageList<Book> pageList = new PageList<>();
    //
    //pageList.setData(bookList);
    //pageList.setPageInfo(bookPage);

    return null;
  }
}
