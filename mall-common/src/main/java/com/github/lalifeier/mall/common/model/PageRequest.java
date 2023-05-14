package com.github.lalifeier.mall.common.model;

import java.io.Serializable;
import java.util.Map;

public abstract class PageRequest implements Serializable {
  private static final long serialVersionUID = 1L;

  public static final String ASC = "ASC";
  public static final String DESC = "DESC";
  private static final int DEFAULT_PAGE_SIZE = 10;
  private static final String DEFAULT_ORDER_DIRECTION = DESC;
  private static final boolean DEFAULT_NEED_TOTAL_COUNT = true;

  private int pageNum = 1;
  private int pageSize = DEFAULT_PAGE_SIZE;
  private String orderBy;
  private String orderDirection = DEFAULT_ORDER_DIRECTION;
  private String groupBy;
  private boolean needTotalCount = DEFAULT_NEED_TOTAL_COUNT;

  private Map<String, String> filter;

  public Map<String, String> getFilter() {
    return filter;
  }

  public void setFilter(Map<String, String> filter) {
    this.filter = filter;
  }

  public int getPageNum() {
    return Math.max(pageNum, 1);
  }

  public PageRequest setPageNum(int pageNum) {
    this.pageNum = pageNum;
    return this;
  }

  public int getPageSize() {
    return Math.max(pageSize, DEFAULT_PAGE_SIZE);
  }

  public PageRequest setPageSize(int pageSize) {
    this.pageSize = Math.max(pageSize, DEFAULT_PAGE_SIZE);
    return this;
  }

  public int getOffset() {
    return (getPageNum() - 1) * getPageSize();
  }

  public String getOrderBy() {
    return orderBy;
  }

  public PageRequest setOrderBy(String orderBy) {
    this.orderBy = orderBy;
    return this;
  }

  public String getOrderDirection() {
    return orderDirection;
  }

  public PageRequest setOrderDirection(String orderDirection) {
    if (ASC.equalsIgnoreCase(orderDirection) || DESC.equalsIgnoreCase(orderDirection)) {
      this.orderDirection = orderDirection;
    }
    return this;
  }

  public String getGroupBy() {
    return groupBy;
  }

  public void setGroupBy(String groupBy) {
    this.groupBy = groupBy;
  }

  public boolean isNeedTotalCount() {
    return needTotalCount;
  }

  public void setNeedTotalCount(boolean needTotalCount) {
    this.needTotalCount = needTotalCount;
  }

  @Override
  public String toString() {
    return "PageQuery{" +
      "pageNum=" + pageNum +
      ", pageSize=" + pageSize +
      ", orderBy='" + orderBy + '\'' +
      ", orderDirection='" + orderDirection + '\'' +
      ", groupBy='" + groupBy + '\'' +
      ", needTotalCount=" + needTotalCount +
      '}';
  }
}
