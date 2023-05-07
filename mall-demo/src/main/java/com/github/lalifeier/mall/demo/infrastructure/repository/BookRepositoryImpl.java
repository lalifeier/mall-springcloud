package com.github.lalifeier.mall.demo.infrastructure.repository;

import java.util.List;

import com.github.lalifeier.mall.mybatispluss.converter.MybatisPlusPageConverter;
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
import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryImpl implements BookRepository {

  private final BookMapper bookMapper;

  private final BookConverter bookConverter;

  public BookRepositoryImpl(BookMapper bookMapper) {
    this.bookMapper = bookMapper;
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
    Long id = bookId.getValue();
    BookPO bookPO = bookMapper.selectById(id);
    return bookConverter.convert(bookPO);
  }

  @Override
  public void remove(@NotNull BookDO aggregate) {
    BookPO bookPO = bookConverter.convert(aggregate);
    bookMapper.deleteById(bookPO);
  }

  @Override
  public void save(@NotNull BookDO aggregate) {
    BookPO bookPO = bookConverter.convert(aggregate);

    if (aggregate.getId() != null && aggregate.getId().getValue() > 0) {
      bookMapper.updateById(bookPO);
    } else {
      bookMapper.insert(bookPO);
      aggregate.setId(new BookId(bookPO.getId()));
    }
  }

  @Override
  public List<BookDO> findAll() {
    LambdaQueryWrapper<BookPO> queryWrapper = Wrappers.lambdaQuery();

    // queryWrapper.eq(BookPO::getId, "");
    queryWrapper.orderByDesc(BookPO::getId);

    List<BookPO> bookPOList = bookMapper.selectList(queryWrapper);

    return bookConverter.convertList(bookPOList);
  }

  public PageList<BookDO> page(Integer pageNum, Integer pageSize) {
    Page<BookPO> page = new Page<>(pageNum, pageSize);
    LambdaQueryWrapper<BookPO> queryWrapper = Wrappers.lambdaQuery();

    queryWrapper.orderByDesc(BookPO::getId);

    IPage<BookPO> bookPage = bookMapper.selectPage(page, queryWrapper);

    // IPage<BookPO> bookPage = bookMapper.selectPage(new Page<>(pageNum, pageSize),
    // Wrappers.<BookPO>lambdaQuery().orderByDesc(BookPO::getId));

    return MybatisPlusPageConverter.convert(bookPage, bookConverter::convert);
  }
}
