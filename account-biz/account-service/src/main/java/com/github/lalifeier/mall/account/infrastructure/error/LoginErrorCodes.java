package com.github.lalifeier.mall.account.infrastructure.error;

import com.github.lalifeier.mall.common.error.api.ErrorCode;
import com.github.lalifeier.mall.common.error.manager.ErrorManager;

import lombok.Getter;

@Getter
public enum LoginErrorCodes implements ErrorCode {

  USER_NOT_EXIST(0, "用户名不存在"), // 错误码: 10100

  PASSWORD_ERROR(1, "密码错误"); // 错误码: 10101

  private final int nodeNum;
  private final String message;

  LoginErrorCodes(int nodeNum, String message) {
    this.nodeNum = nodeNum;
    this.message = message;
    ErrorManager.register(AccountProjectCodes.Account, this);
  }
}
