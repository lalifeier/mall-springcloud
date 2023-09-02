package com.github.lalifeier.mall.cloud.account.infrastructure.enums;

import com.github.lalifeier.mall.cloud.common.api.ProjectModule;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProjectModuleEnum implements ProjectModule {
  /** 登录模块 */
  LOGIN(1, 1, "帐号服务", "登录模块"),

  /** 注册模块 */
  REGISTER(1, 2, "帐号服务", "注册模块"),

  /** 帐号模块 */
  Account(1, 3, "帐号服务", "帐号模块");

  /** 项目代码 */
  private final int projectCode;

  /** 模块代码 */
  private final int moduleCode;

  /** 项目名称 */
  private final String projectName;

  /** 模块名称 */
  private final String moduleName;
}
