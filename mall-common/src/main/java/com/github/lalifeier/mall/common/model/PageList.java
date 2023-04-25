package com.github.lalifeier.mall.common.model;

import lombok.Data;

import java.util.List;

@Data
public class PageList<T>  {
  private List<T> data;

  private PageInfo pageInfo;

  //public  PageList(List<T> data, PageInfo pageInfo) {
  //  this.data = data;
  //  this.pageInfo = pageInfo;
  //}
  //
  //public  PageList(com.github.pagehelper.Page<T> page) {
  //  this.data = page.getResult();
  //  this.pageInfo =new PageInfo(page.getPageNum(), page.getPageSize(), page.getTotal());
  //}
  //

  //public  PageList(org.springframework.data.domain.Page<T> page) {
  //  this.data = page.getContent();
  //  this.pageInfo =new PageInfo(page.getNumber(), page.getSize(), page.getTotalElements());
  //}
  //

  public  PageList(com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> page) {
    this.data = page.getRecords();
    this.pageInfo = new PageInfo((int) page.getCurrent(), (int) page.getSize(), page.getTotal());
  }

  public  PageList(com.baomidou.mybatisplus.core.metadata.IPage<T> page) {
    this.data = page.getRecords();
    this.pageInfo = new PageInfo((int) page.getCurrent(), (int) page.getSize(), page.getTotal());
  }
}
