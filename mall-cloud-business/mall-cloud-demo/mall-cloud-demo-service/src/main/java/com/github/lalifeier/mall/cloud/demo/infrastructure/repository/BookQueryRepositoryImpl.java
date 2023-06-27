package com.github.lalifeier.mall.cloud.demo.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lalifeier.mall.cloud.common.model.query.PageQuery;
import com.github.lalifeier.mall.cloud.common.model.query.Pagination;
import com.github.lalifeier.mall.cloud.demo.applicaiton.book.service.repository.BookQueryRepository;
import com.github.lalifeier.mall.cloud.demo.domain.book.model.entity.BookEntity;
import com.github.lalifeier.mall.cloud.demo.infrastructure.repository.mybatis.book.converter.BookConverter;
import com.github.lalifeier.mall.cloud.demo.infrastructure.repository.mybatis.book.mapper.BookMapper;
import com.github.lalifeier.mall.cloud.demo.infrastructure.repository.mybatis.book.po.BookPO;
import com.github.lalifeier.mall.cloud.mybatisplus.converter.PageConverter;
import com.github.lalifeier.mall.cloud.mybatisplus.utils.QueryWrapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class BookQueryRepositoryImpl implements BookQueryRepository {
  private final BookMapper bookMapper;

  private final BookConverter bookConverter = BookConverter.INSTANCE;

  public BookQueryRepositoryImpl(BookMapper bookMapper) {
    this.bookMapper = bookMapper;
  }

  @Override
  public List<BookEntity> list() {
    LambdaQueryWrapper<BookPO> queryWrapper = Wrappers.lambdaQuery();

    // queryWrapper.eq(BookPO::getId, "");
    queryWrapper.orderByDesc(BookPO::getId);

    List<BookPO> bookPOList = bookMapper.selectList(queryWrapper);

    return bookConverter.convertList(bookPOList);
  }

  @Override
  public Pagination<BookEntity> pageList(PageQuery request) {
    Page<BookPO> page = new Page<>(request.getPageNum(), request.getPageSize());

    QueryWrapper<BookPO> queryWrapper = QueryWrapperUtils.buildQueryWrapper(BookPO.class, request);

    IPage<BookPO> bookPage = bookMapper.selectPage(page, queryWrapper);

    return PageConverter.convert(bookPage, bookConverter::convert);
  }
}
