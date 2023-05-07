package com.github.lalifeier.mall.mybatispluss.converter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lalifeier.mall.common.model.PageInfo;
import com.github.lalifeier.mall.common.model.PageList;


import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MybatisPlusPageConverter {
  public static <T, R> PageList<R> convert(com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> page,
                                           Function<T, R> mapper) {
    List<R> records = page.getRecords().stream().map(mapper).collect(Collectors.toList());
    PageInfo pageInfo = new PageInfo((int) page.getCurrent(), (int) page.getSize(), page.getTotal());
    return new PageList<>(records, pageInfo);
  }

  public static <T, R> PageList<R> convert(IPage<T> page, Function<T, R> mapper) {
    List<R> records = page.getRecords().stream().map(mapper).collect(Collectors.toList());
    PageInfo pageInfo = new PageInfo((int) page.getCurrent(), (int) page.getSize(), page.getTotal());
    return new PageList<>(records, pageInfo);
  }
}
