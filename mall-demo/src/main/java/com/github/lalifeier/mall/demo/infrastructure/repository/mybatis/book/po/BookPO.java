package com.github.lalifeier.mall.demo.infrastructure.repository.mybatis.book.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.lalifeier.mall.common.po.BasePO;

import lombok.Data;

@Data
@TableName(value = "book")
public class BookPO extends BasePO {

  @TableField("id")
  @TableId(type = IdType.AUTO)
  private Long id;
}
