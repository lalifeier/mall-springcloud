package com.github.lalifeier.mall.account.infrastructure.persistence.mybatis.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value = "account_user")
public class AccountUserPO {

  @TableField("id")
  @TableId(type = IdType.AUTO)
  private Long id;

  private String username;

  private String password;
}
