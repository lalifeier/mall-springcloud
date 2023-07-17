package com.github.lalifeier.mall.cloud.auth.infrastructure.error;


import com.github.lalifeier.mall.cloud.common.api.ProjectModule;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProjectModuleCode implements ProjectModule {
  /**
   * 登录模块
   */
  LOGIN(1, 1, "用户中心", "登录模块"),

  /**
   * 用户管理模块
   */
  USER(1, 2, "用户中心", "用户模块");

  private final int projectCode;
  private final int moduleCode;
  private final String projectName;
  private final String moduleName;
}
