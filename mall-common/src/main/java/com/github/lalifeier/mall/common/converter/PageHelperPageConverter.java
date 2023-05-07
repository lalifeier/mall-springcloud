package com.github.lalifeier.mall.common.converter;

//import java.util.List;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//
//import com.github.lalifeier.mall.common.model.PageInfo;
//import com.github.lalifeier.mall.common.model.PageList;
//
//public class PageHelperPageConverter {
//  public static <T, R> PageList<R> convert(com.github.pagehelper.Page<T> page, Function<T, R> mapper) {
//    List<R> records = page.stream().map(mapper).collect(Collectors.toList());
//    PageInfo pageInfo = new PageInfo(page.getPageNum(), page.getPageSize(), page.getTotal());
//    return new PageList<>(records, pageInfo);
//  }
//}
