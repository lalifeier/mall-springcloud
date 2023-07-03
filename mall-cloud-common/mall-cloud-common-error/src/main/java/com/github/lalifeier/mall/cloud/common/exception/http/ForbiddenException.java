package com.github.lalifeier.mall.cloud.common.exception.http;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.api.ProjectModule;
import com.github.lalifeier.mall.cloud.common.exception.BaseException;
import com.github.lalifeier.mall.cloud.common.manager.ErrorInfo;
import com.github.lalifeier.mall.cloud.common.system.SystemProjectModule;

public class ForbiddenException extends BaseException {
  protected ForbiddenException(String message) {
    super(message);
  }

  protected ForbiddenException(String message, Throwable cause) {
    super(message, cause);
  }

  protected ForbiddenException(Throwable cause) {
    super(cause);
  }

  protected ForbiddenException(ErrorInfo errorInfo) {
    super(errorInfo);
  }

  protected ForbiddenException(ErrorCode ErrorCode) {
    super(ErrorCode);
  }

  protected ForbiddenException(ErrorCode ErrorCode, Object... args) {
    super(ErrorCode, args);
  }

  public ProjectModule projectModule() {
    return SystemProjectModule.INSTANCE;
  }
}
