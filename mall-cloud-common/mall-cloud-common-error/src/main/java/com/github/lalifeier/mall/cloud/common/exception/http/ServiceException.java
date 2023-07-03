package com.github.lalifeier.mall.cloud.common.exception.http;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.api.ProjectModule;
import com.github.lalifeier.mall.cloud.common.exception.BaseException;
import com.github.lalifeier.mall.cloud.common.manager.ErrorInfo;
import com.github.lalifeier.mall.cloud.common.system.SystemProjectModule;

public class ServiceException extends BaseException {
  protected ServiceException(String message) {
    super(message);
  }

  protected ServiceException(String message, Throwable cause) {
    super(message, cause);
  }

  protected ServiceException(Throwable cause) {
    super(cause);
  }

  protected ServiceException(ErrorInfo errorInfo) {
    super(errorInfo);
  }

  protected ServiceException(ErrorCode ErrorCode) {
    super(ErrorCode);
  }

  protected ServiceException(ErrorCode ErrorCode, Object... args) {
    super(ErrorCode, args);
  }

  @Override
  public ProjectModule projectModule() {
    return SystemProjectModule.INSTANCE;
  }
}
