package com.github.lalifeier.api;

import lombok.Data;

import java.io.Serializable;
@Data
public class PageInfo<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int pageNum;
    private int pageSize;
    private long totalCount;
    private int totalPages;

    public int getTotalPages() {
        return (int) (this.totalCount % this.pageSize == 0 ? this.totalCount
                        / this.pageSize : (this.totalCount / this.pageSize) + 1);
    }

    public PageInfo(int pageNum, int pageSize, long totalCount) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPages = this.getTotalPages();
    }
}
