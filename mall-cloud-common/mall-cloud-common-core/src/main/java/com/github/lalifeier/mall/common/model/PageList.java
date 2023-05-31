package com.github.lalifeier.mall.common.model;

import java.util.List;

public class PageList<T> {
  private List<T> data;
  private PageInfo pageInfo;

  public PageList(List<T> data, PageInfo pageInfo) {
    this.data = data;
    this.pageInfo = pageInfo;
  }

  public List<T> getData() {
    return data;
  }

  public PageInfo getPageInfo() {
    return pageInfo;
  }
}
