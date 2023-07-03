package com.github.lalifeier.mall.cloud.common.exception.http;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.api.ProjectModule;
import com.github.lalifeier.mall.cloud.common.exception.BaseException;
import com.github.lalifeier.mall.cloud.common.manager.ErrorInfo;
import com.github.lalifeier.mall.cloud.common.system.SystemProjectModule;

public class NotFoundException extends BaseException {
  protected NotFoundException(String message) {
    super(message);
  }

  protected NotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  protected NotFoundException(Throwable cause) {
    super(cause);
  }

  protected NotFoundException(ErrorInfo errorInfo) {
    super(errorInfo);
  }

  protected NotFoundException(ErrorCode ErrorCode) {
    super(ErrorCode);
  }

  protected NotFoundException(ErrorCode ErrorCode, Object... args) {
    super(ErrorCode, args);
  }

  public ProjectModule projectModule() {
    return SystemProjectModule.INSTANCE;
  }
}
