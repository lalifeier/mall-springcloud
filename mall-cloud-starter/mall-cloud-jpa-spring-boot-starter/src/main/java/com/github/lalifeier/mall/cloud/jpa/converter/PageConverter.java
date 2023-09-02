package com.github.lalifeier.mall.cloud.jpa.converter;

import com.github.lalifeier.mall.cloud.common.model.query.PageInfo;
import com.github.lalifeier.mall.cloud.common.model.query.Pagination;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PageConverter {
  public static <T, R> Pagination<R> convert(org.springframework.data.domain.Page<T> page,
      Function<T, R> mapper) {
    List<R> records = page.getContent().stream().map(mapper).collect(Collectors.toList());
    PageInfo pageInfo = new PageInfo(page.getNumber() + 1, page.getSize(), page.getTotalElements());
    return new Pagination<>(records, pageInfo);
  }
}
