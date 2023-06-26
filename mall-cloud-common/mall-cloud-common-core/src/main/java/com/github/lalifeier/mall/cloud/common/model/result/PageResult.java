package com.github.lalifeier.mall.cloud.common.model.result;

import com.github.lalifeier.mall.cloud.common.model.query.PageInfo;
import com.github.lalifeier.mall.cloud.common.model.query.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class PageResult<T> extends BaseResult {

  private List<T> data;

  private PageInfo pageInfo;

  public static <T> PageResult<T> success(List<T> data, int pageNum, int pageSize, int totalCount) {
    PageResult<T> pageResult = new PageResult<>();
    pageResult.setSuccess(true);
    pageResult.setData(data);
    pageResult.setPageInfo(new PageInfo(pageNum, pageSize, totalCount));
    return pageResult;
  }

  public static <T> PageResult<T> success(List<T> data, PageInfo pageInfo) {
    PageResult<T> pageResult = new PageResult<>();
    pageResult.setSuccess(true);
    pageResult.setData(data);
    pageResult.setPageInfo(pageInfo);
    return pageResult;
  }

  public static <T> PageResult<T> success(Pagination<T> pagination) {
    PageResult<T> pageResult = new PageResult<>();
    pageResult.setSuccess(true);
    pageResult.setData(pagination.getData());
    pageResult.setPageInfo(pagination.getPageInfo());
    return pageResult;
  }

  // public static <T> PageResult<T>
  // success(org.springframework.data.domain.Page<T> page) {
  // PageResult<T> pageResult = new PageResult<>();
  // pageResult.setSuccess(true);
  // pageResult.setData(page.getContent());
  // pageResult.setPageInfo(new PageInfo(page.getNumber(), page.getSize(),
  // page.getTotalElements()));
  // return pageResult;
  // }

  // public static <T> PageResult<T> success(com.github.pagehelper.Page<T> page) {
  // PageResult<T> response = new PageResult<>();
  // response.setSuccess(true);
  // response.setData(page.getResult());
  // response.setPageInfo(new PageInfo(page.getPageNum(), page.getPageSize(),
  // page.getTotal()));
  // return response;
  // }

  // public static <T> PageResult<T>
  // success(com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> page) {
  // PageResult<T> pageResult = new PageResult<>();
  // pageResult.setSuccess(true);
  // pageResult.setData(page.getRecords());
  // pageResult.setPageInfo(new PageInfo((int) page.getCurrent(), (int)
  // page.getSize(), page.getTotal()));
  // return pageResult;
  // }
}
