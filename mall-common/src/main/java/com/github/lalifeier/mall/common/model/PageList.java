package com.github.lalifeier.mall.common.model;

import java.util.List;

public class PageList<T> {
  private List<T> records;
  private PageInfo pageInfo;

  public PageList(List<T> records, PageInfo pageInfo) {
    this.records = records;
    this.pageInfo = pageInfo;
  }

  public List<T> getRecords() {
    return records;
  }

  public PageInfo getPageInfo() {
    return pageInfo;
  }
}
