package com.github.lalifeier.mall.cloud.common.exception;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.api.ProjectModule;
import com.github.lalifeier.mall.cloud.common.system.SystemErrorCode;
import com.github.lalifeier.mall.cloud.common.system.SystemProjectModule;

public class ValidationException extends BaseException {

  public ValidationException(Throwable cause) {
    super(cause);
  }

  public ValidationException(String description) {
    super(description);
  }

  public ValidationException(int code, String description) {
    super(code, description);
  }

  public ValidationException(ErrorCode errorCode) {
    super(errorCode);
  }

  public ValidationException(ErrorCode errorCode, Object... args) {
    super(errorCode, args);
  }

  public ProjectModule projectModule() {
    return SystemProjectModule.INSTANCE;
  }

  @Override
  public ErrorCode defaultErrorCode() {
    return SystemErrorCode.BAD_REQUEST;
  }
}
