package com.github.lalifeier.mall.cloud.common.exception;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.api.ProjectModule;
import com.github.lalifeier.mall.cloud.common.manager.ErrorInfo;
import com.github.lalifeier.mall.cloud.common.system.SystemProjectModule;

public class BusinessException extends BaseException {
  protected BusinessException(String message) {
    super(message);
  }

  protected BusinessException(String message, Throwable cause) {
    super(message, cause);
  }

  protected BusinessException(Throwable cause) {
    super(cause);
  }

  protected BusinessException(ErrorInfo errorInfo) {
    super(errorInfo);
  }

  protected BusinessException(ErrorCode ErrorCode) {
    super(ErrorCode);
  }

  protected BusinessException(ErrorCode ErrorCode, Object... args) {
    super(ErrorCode, args);
  }

  public ProjectModule projectModule() {
    return SystemProjectModule.INSTANCE;
  }
}

