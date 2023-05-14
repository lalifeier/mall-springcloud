package com.github.lalifeier.mall.demo.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lalifeier.mall.common.model.PageList;
import com.github.lalifeier.mall.common.model.PageRequest;
import com.github.lalifeier.mall.demo.domain.book.model.entity.BookDO;
import com.github.lalifeier.mall.demo.domain.book.model.valueobject.BookId;
import com.github.lalifeier.mall.demo.domain.book.repository.BookRepository;
import com.github.lalifeier.mall.demo.infrastructure.repository.mybatis.book.converter.BookConverter;
import com.github.lalifeier.mall.demo.infrastructure.repository.mybatis.book.mapper.BookMapper;
import com.github.lalifeier.mall.demo.infrastructure.repository.mybatis.book.po.BookPO;
import com.github.lalifeier.mall.mybatispluss.converter.MybatisPlusPageConverter;
import com.github.lalifeier.mall.mybatispluss.utils.QueryWrapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class BookRepositoryImpl implements BookRepository {

  private final BookMapper bookMapper;

  private final BookConverter bookConverter = BookConverter.INSTANCE;

  public BookRepositoryImpl(BookMapper bookMapper) {
    this.bookMapper = bookMapper;
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

    log.info(bookPO.toString());

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

  public PageList<BookDO> page(PageRequest request) {
    Page<BookPO> page = new Page<>(request.getPageNum(), request.getPageSize());

    QueryWrapper<BookPO> queryWrapper = new QueryWrapper<BookPO>();

    QueryWrapperUtils.buildQueryWrapper(queryWrapper, request.getFilter(), BookPO.class, request.getOrderBy(), request.getOrderDirection());

    IPage<BookPO> bookPage = bookMapper.selectPage(page, queryWrapper);

    return MybatisPlusPageConverter.convert(bookPage, bookConverter::convert);
  }
}
