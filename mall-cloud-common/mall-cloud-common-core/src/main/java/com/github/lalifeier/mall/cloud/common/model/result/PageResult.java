package com.github.lalifeier.mall.cloud.common.model.result;

import com.github.lalifeier.mall.cloud.common.enums.ErrorCodeEnum;
import com.github.lalifeier.mall.cloud.common.model.query.PageInfo;
import com.github.lalifeier.mall.cloud.common.model.query.Pagination;
import java.util.List;

public class PageResult<T> extends Result<List<T>> {
    private PageInfo pageInfo;

    public PageResult(List<T> data, PageInfo pageInfo) {
        super(ErrorCodeEnum.SUCCESS.getCode(), ErrorCodeEnum.SUCCESS.getStatus(), null, null, data);
        this.pageInfo = pageInfo;
    }

    public static <T> PageResult<T> success(List<T> data, int pageNum, int pageSize, int totalCount) {
        PageInfo pageInfo = new PageInfo(pageNum, pageSize, totalCount);
        return new PageResult<>(data, pageInfo);
    }

    public static <T> PageResult<T> success(List<T> data, PageInfo pageInfo) {
        return new PageResult<>(data, pageInfo);
    }

    public static <T> PageResult<T> success(Pagination<T> pagination) {
        return new PageResult<>(pagination.getData(), pagination.getPageInfo());
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }
}
