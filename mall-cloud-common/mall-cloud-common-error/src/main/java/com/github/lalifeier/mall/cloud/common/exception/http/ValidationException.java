package com.github.lalifeier.mall.cloud.common.exception.http;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.api.ProjectModule;
import com.github.lalifeier.mall.cloud.common.exception.BaseException;
import com.github.lalifeier.mall.cloud.common.manager.ErrorInfo;
import com.github.lalifeier.mall.cloud.common.system.SystemProjectModule;

public class ValidationException extends BaseException {
  protected ValidationException(String message) {
    super(message);
  }

  protected ValidationException(String message, Throwable cause) {
    super(message, cause);
  }

  protected ValidationException(Throwable cause) {
    super(cause);
  }

  protected ValidationException(ErrorInfo errorInfo) {
    super(errorInfo);
  }

  protected ValidationException(ErrorCode ErrorCode) {
    super(ErrorCode);
  }

  protected ValidationException(ErrorCode ErrorCode, Object... args) {
    super(ErrorCode, args);
  }

  public ProjectModule projectModule() {
    return SystemProjectModule.INSTANCE;
  }
}
