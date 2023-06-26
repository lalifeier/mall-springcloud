package com.github.lalifeier.mall.cloud.common.model;

import java.io.Serializable;
import java.util.Map;

public abstract class PageQuery implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final int DEFAULT_PAGE_SIZE = 10;

  private int pageNum = 1;
  private int pageSize = DEFAULT_PAGE_SIZE;

  private String orderBy;

  private String[] columns;

  private Map<String, String> filter;

  public Map<String, String> getFilter() {
    return filter;
  }

  public void setFilter(Map<String, String> filter) {
    this.filter = filter;
  }

  public String[] getColumns() {
    return columns;
  }

  public void setColumns(String[] columns) {
    this.columns = columns;
  }

  public int getPageNum() {
    return Math.max(pageNum, 1);
  }

  public PageQuery setPageNum(int pageNum) {
    this.pageNum = pageNum;
    return this;
  }

  public int getPageSize() {
    return Math.max(pageSize, DEFAULT_PAGE_SIZE);
  }

  public PageQuery setPageSize(int pageSize) {
    this.pageSize = Math.max(pageSize, DEFAULT_PAGE_SIZE);
    return this;
  }

  public int getOffset() {
    return (getPageNum() - 1) * getPageSize();
  }

  public String getOrderBy() {
    return orderBy;
  }

  public PageQuery setOrderBy(String orderBy) {
    this.orderBy = orderBy;
    return this;
  }

  @Override
  public String toString() {
    return "PageRequest{" +
      "pageNum=" + pageNum +
      ", pageSize=" + pageSize +
      ", orderBy='" + orderBy + '\'' +
      '}';
  }
}
