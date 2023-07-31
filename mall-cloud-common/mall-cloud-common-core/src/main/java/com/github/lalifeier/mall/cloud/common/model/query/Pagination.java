package com.github.lalifeier.mall.cloud.common.model.query;

import java.util.List;

public class Pagination<T> {
    private List<T> data;
    private PageInfo pageInfo;

    public Pagination(List<T> data, PageInfo pageInfo) {
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
