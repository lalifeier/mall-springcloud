package com.github.lalifeier.mall.account.infrastructure.error;

import com.github.lalifeier.mall.common.error.api.ProjectModule;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountProjectCodes implements ProjectModule {
  /**
   * 登录模块
   */
  LOGIN(1, 1, "帐号服务", "登录模块"),

  /**
   * 注册模块
   */
  REFISTER(1, 2, "帐号服务", "注册模块"),

  /**
   * 帐号模块
   */
  Account(1, 3, "帐号服务", "帐号模块");

  private final int projectCode;
  private final int moduleCode;
  private final String projectName;
  private final String moduleName;
}
