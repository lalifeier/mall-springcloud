package com.github.lalifeier.mall.cloud.account.infrastructure.error;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.manager.ErrorManager;
import lombok.Getter;

@Getter
public enum RegisterErrorCode implements ErrorCode {

  B_USER_EXIST(0, "用户已存在");

  private final int nodeNum;
  private final String description;
  private final String message;

  RegisterErrorCode(int nodeNum, String description) {
    this.nodeNum = nodeNum;
    this.description = description;
    this.message = description;
    ErrorManager.register(ProjectModuleCode.REGISTER, this);
  }

  RegisterErrorCode(int nodeNum, String description, String message) {
    this.nodeNum = nodeNum;
    this.description = description;
    this.message = message;
    ErrorManager.register(ProjectModuleCode.REGISTER, this);
  }
}
