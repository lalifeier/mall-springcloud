package com.github.lalifeier.mall.cloud.demo.infrastructure.persistence.mybatis.book.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.lalifeier.mall.cloud.mybatisplus.po.BasePO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "t_book")
public class BookPO extends BasePO<BookPO> {
  private String name;
  //private String author;
  //private String publisher;
}
