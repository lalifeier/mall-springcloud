package com.github.lalifeier.mall.cloud.common.model.query;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractPageQuery extends AbstractQuery {
    private static final int DEFAULT_PAGE_NUM = 1;
    private static final int DEFAULT_PAGE_SIZE = 10;

    @Min(value = 1, message = "pageNum必须大于等于1")
    private int pageNum = DEFAULT_PAGE_NUM;

    @Min(value = 1, message = "pageSize必须大于等于1")
    private int pageSize = DEFAULT_PAGE_SIZE;

    public int getPageNum() {
        return Math.max(pageNum, DEFAULT_PAGE_NUM);
    }

    public int getPageSize() {
        return Math.max(pageSize, DEFAULT_PAGE_SIZE);
    }

    public int getOffset() {
        int pageNum = getPageNum();
        int pageSize = getPageSize();
        return (pageNum - 1) * pageSize;
    }
}
