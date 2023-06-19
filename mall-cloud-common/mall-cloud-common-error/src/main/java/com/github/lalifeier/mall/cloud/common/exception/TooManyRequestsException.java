package com.github.lalifeier.mall.cloud.common.exception;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.api.ProjectModule;
import com.github.lalifeier.mall.cloud.common.manager.ErrorInfo;
import com.github.lalifeier.mall.cloud.common.system.SystemProjectModule;

public class TooManyRequestsException extends BaseException {
  public TooManyRequestsException(String message) {
    super(message);
  }

  public TooManyRequestsException(String message, Throwable cause) {
    super(message, cause);
  }

  public TooManyRequestsException(Throwable cause) {
    super(cause);
  }

  public TooManyRequestsException(ErrorInfo errorInfo) {
    super(errorInfo);
  }

  public TooManyRequestsException(ErrorCode ErrorCode) {
    super(ErrorCode);
  }

  public TooManyRequestsException(ErrorCode ErrorCode, Object... args) {
    super(ErrorCode, args);
  }

  @Override
  public ProjectModule projectModule() {
    return SystemProjectModule.INSTANCE;
  }
}
