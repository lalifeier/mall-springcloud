package com.github.lalifeier.mall.cloud.common.exception;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.api.ProjectModule;
import com.github.lalifeier.mall.cloud.common.system.SystemErrorCode;
import com.github.lalifeier.mall.cloud.common.system.SystemProjectModule;

public class NotFoundException extends BaseException {

  public NotFoundException(Throwable cause) {
    super(cause);
  }

  public NotFoundException(String description) {
    super(description);
  }

  public NotFoundException(int code, String description) {
    super(code, description);
  }

  public NotFoundException(ErrorCode errorCode) {
    super(errorCode);
  }

  public NotFoundException(ErrorCode errorCode, Object... args) {
    super(errorCode, args);
  }

  public ProjectModule projectModule() {
    return SystemProjectModule.INSTANCE;
  }

  @Override
  public ErrorCode defaultErrorCode() {
    return SystemErrorCode.NOT_FOUND;
  }
}
