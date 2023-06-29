package com.github.lalifeier.mall.cloud.account.infrastructure.persistence.mybatis.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.lalifeier.mall.cloud.mybatisplus.po.BasePO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "account_user")
public class AccountUserPO extends BasePO<AccountUserPO> {
  /**
   * 用户名
   */
  private String username;

  /**
   * 密码
   */
  private String password;

  /**
   * 邮箱
   */
  private String email;

  /**
   * 手机号
   */
  private String phone;

  /**
   * 帐号状态：0 - 无效，1 - 有效
   */
  private int status;
}
