package com.github.lalifeier.mall.cloud.mybatisplus.converter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lalifeier.mall.cloud.common.model.query.PageInfo;
import com.github.lalifeier.mall.cloud.common.model.query.Pagination;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PageConverter {
  public static <T, R> Pagination<R> convert(com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> page,
                                             Function<T, R> mapper) {
    List<R> records = page.getRecords().stream().map(mapper).collect(Collectors.toList());
    PageInfo pageInfo = new PageInfo((int) page.getCurrent(), (int) page.getSize(), page.getTotal());
    return new Pagination<>(records, pageInfo);
  }

  public static <T, R> Pagination<R> convert(IPage<T> page, Function<T, R> mapper) {
    List<R> records = page.getRecords().stream().map(mapper).collect(Collectors.toList());
    PageInfo pageInfo = new PageInfo((int) page.getCurrent(), (int) page.getSize(), page.getTotal());
    return new Pagination<>(records, pageInfo);
  }
}
