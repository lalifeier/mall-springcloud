package com.github.lalifeier.mall.account.infrastructure.exception;

import com.github.lalifeier.mall.common.error.api.ErrorCode;
import com.github.lalifeier.mall.common.error.manager.ErrorManager;

import lombok.Getter;

@Getter
public enum RegisterErrorCodes implements ErrorCode {

  USER_EXIST(0, "用户已存在");

  private final int nodeNum;
  private final String message;

  RegisterErrorCodes(int nodeNum, String message) {
    this.nodeNum = nodeNum;
    this.message = message;
    ErrorManager.register(AccountProjectCodes.REGISTER, this);
  }
}
