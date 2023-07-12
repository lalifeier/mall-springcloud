package com.github.lalifeier.mall.cloud.common.model.query;

import lombok.Data;

@Data
public class PageInfo {
  private int pageNum;
  private int pageSize;
  private long totalCount;
  private int totalPages;
  private boolean hasNext;

  public int getTotalPages() {
    return (int) (this.totalCount % this.pageSize == 0 ? this.totalCount
      / this.pageSize : (this.totalCount / this.pageSize) + 1);
  }

  private boolean getHasNext() {
    return pageNum < totalPages;
  }

  public PageInfo(int pageNum, int pageSize, long totalCount) {
    this.pageNum = pageNum;
    this.pageSize = pageSize;
    this.totalCount = totalCount;
    this.totalPages = this.getTotalPages();
    this.hasNext = this.getHasNext();
  }
}
