package com.github.lalifeier.mall.cloud.common.exception;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.api.ProjectModule;
import com.github.lalifeier.mall.cloud.common.system.SystemErrorCode;
import com.github.lalifeier.mall.cloud.common.system.SystemProjectModule;

public class UnauthorizedException extends BaseException {

  public UnauthorizedException(Throwable cause) {
    super(cause);
  }

  public UnauthorizedException(String description) {
    super(description);
  }

  public UnauthorizedException(int code, String description) {
    super(code, description);
  }

  public UnauthorizedException(ErrorCode errorCode) {
    super(errorCode);
  }

  public UnauthorizedException(ErrorCode errorCode, Object... args) {
    super(errorCode, args);
  }

  public ProjectModule projectModule() {
    return SystemProjectModule.INSTANCE;
  }

  @Override
  public ErrorCode defaultErrorCode() {
    return SystemErrorCode.UNAUTHORIZED;
  }
}
