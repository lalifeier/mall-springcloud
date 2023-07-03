package com.github.lalifeier.mall.cloud.common.exception.http;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.api.ProjectModule;
import com.github.lalifeier.mall.cloud.common.exception.BaseException;
import com.github.lalifeier.mall.cloud.common.manager.ErrorInfo;
import com.github.lalifeier.mall.cloud.common.system.SystemProjectModule;

public class UnauthorizedException extends BaseException {
  protected UnauthorizedException(String message) {
    super(message);
  }

  protected UnauthorizedException(String message, Throwable cause) {
    super(message, cause);
  }

  protected UnauthorizedException(Throwable cause) {
    super(cause);
  }

  protected UnauthorizedException(ErrorInfo errorInfo) {
    super(errorInfo);
  }

  protected UnauthorizedException(ErrorCode ErrorCode) {
    super(ErrorCode);
  }

  protected UnauthorizedException(ErrorCode ErrorCode, Object... args) {
    super(ErrorCode, args);
  }

  public ProjectModule projectModule() {
    return SystemProjectModule.INSTANCE;
  }
}
