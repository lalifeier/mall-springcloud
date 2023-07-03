package com.github.lalifeier.mall.cloud.common.exception.http;


import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.api.ProjectModule;
import com.github.lalifeier.mall.cloud.common.exception.BaseException;
import com.github.lalifeier.mall.cloud.common.manager.ErrorInfo;
import com.github.lalifeier.mall.cloud.common.system.SystemProjectModule;

public class BadRequestException extends BaseException {

  protected BadRequestException(String message) {
    super(message);
  }

  protected BadRequestException(String message, Throwable cause) {
    super(message, cause);
  }

  protected BadRequestException(Throwable cause) {
    super(cause);
  }

  protected BadRequestException(ErrorInfo errorInfo) {
    super(errorInfo);
  }

  protected BadRequestException(ErrorCode errorCode) {
    super(errorCode);
  }

  protected BadRequestException(ErrorCode errorCode, Object... args) {
    super(errorCode, args);
  }

  @Override
  public ProjectModule projectModule() {
    return SystemProjectModule.INSTANCE;
  }
}
