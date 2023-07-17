package com.github.lalifeier.mall.cloud.account.infrastructure.exception.code;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.manager.ErrorManager;
import lombok.Getter;

@Getter
public enum LoginErrorCode implements ErrorCode {
  B_USER_NOT_EXIST(1, "用户名不存在"),

  B_USER_PASSWORD_ERROR(2, "用户名或密码错误");

  private final int nodeNum;
  private final String description;
  private final String message;

  LoginErrorCode(int nodeNum, String description) {
    this.nodeNum = nodeNum;
    this.description = description;
    this.message = description;
    ErrorManager.register(ProjectModuleCode.LOGIN, this);
  }

  LoginErrorCode(int nodeNum, String description, String message) {
    this.nodeNum = nodeNum;
    this.description = description;
    this.message = message;
    ErrorManager.register(ProjectModuleCode.LOGIN, this);
  }
}
