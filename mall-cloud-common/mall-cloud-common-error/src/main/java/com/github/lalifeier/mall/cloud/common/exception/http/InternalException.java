package com.github.lalifeier.mall.cloud.common.exception.http;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.api.ProjectModule;
import com.github.lalifeier.mall.cloud.common.exception.BaseException;
import com.github.lalifeier.mall.cloud.common.manager.ErrorInfo;
import com.github.lalifeier.mall.cloud.common.system.SystemProjectModule;

public class InternalException extends BaseException {
  protected InternalException(String message) {
    super(message);
  }

  protected InternalException(String message, Throwable cause) {
    super(message, cause);
  }

  protected InternalException(Throwable cause) {
    super(cause);
  }

  protected InternalException(ErrorInfo errorInfo) {
    super(errorInfo);
  }

  protected InternalException(ErrorCode ErrorCode) {
    super(ErrorCode);
  }

  protected InternalException(ErrorCode ErrorCode, Object... args) {
    super(ErrorCode, args);
  }

  public ProjectModule projectModule() {
    return SystemProjectModule.INSTANCE;
  }
}
