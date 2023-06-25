package com.github.lalifeier.mall.cloud.account.infrastructure.exception;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.manager.ErrorManager;

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
