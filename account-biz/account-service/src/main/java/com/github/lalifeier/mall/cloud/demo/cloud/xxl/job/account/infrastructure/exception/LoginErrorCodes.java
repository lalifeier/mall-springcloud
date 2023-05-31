package com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.account.infrastructure.exception;

import com.github.lalifeier.mall.cloud.common.manager.ErrorManager;
import com.github.lalifeier.mall.cloud.common.api.ErrorCode;

import lombok.Getter;

@Getter
public enum LoginErrorCodes implements ErrorCode {
  USER_NOT_EXIST(1, "用户名不存在"),

  PASSWORD_ERROR(2, "密码错误");

  private final int nodeNum;
  private final String message;

  LoginErrorCodes(int nodeNum, String message) {
    this.nodeNum = nodeNum;
    this.message = message;
    ErrorManager.register(AccountProjectCodes.LOGIN, this);
  }
}
