package com.github.lalifeier.mall.cloud.account.infrastructure.enums;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.api.ProjectModule;
import lombok.Getter;

@Getter
public enum RegisterErrorCodeEnum implements ErrorCode {
  B_USER_EXIST(1, "用户已存在");

  private final int httpCode;
  private final int nodeNum;
  private final String description;
  private final String message;

  RegisterErrorCodeEnum(int httpCode, int nodeNum, String description) {
    this(httpCode, nodeNum, description, "");
  }

  RegisterErrorCodeEnum(int httpCode, int nodeNum, String description, String message) {
    this.httpCode = httpCode;
    this.nodeNum = nodeNum;
    this.description = description;
    this.message = message;
  }

  RegisterErrorCodeEnum(int nodeNum, String description) {
    this(500, nodeNum, description, "");
  }

  RegisterErrorCodeEnum(int nodeNum, String description, String message) {
    this(500, nodeNum, description, message);
  }

  @Override
  public ProjectModule getProjectModule() {
    return ProjectModuleEnum.LOGIN;
  }

  @Override
  public String getStatus() {
    return name();
  }
}
