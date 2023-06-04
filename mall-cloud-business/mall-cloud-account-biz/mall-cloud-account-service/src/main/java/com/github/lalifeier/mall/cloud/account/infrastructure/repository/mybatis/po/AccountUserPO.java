package com.github.lalifeier.mall.cloud.account.infrastructure.repository.mybatis.po;

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
   * 手机号
   */
  private String phone;

  /**
   * 邮箱
   */
  private String email;

  /**
   * 帐号状态：0 - 无效，1 - 有效
   */
  private int status;
}
