package com.github.lalifeier.mall.cloud.common.exception;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.api.ProjectModule;
import com.github.lalifeier.mall.cloud.common.manager.ErrorInfo;
import com.github.lalifeier.mall.cloud.common.system.SystemProjectModule;

public class RemoteServiceException extends BaseException {
  protected RemoteServiceException(String message) {
    super(message);
  }

  protected RemoteServiceException(String message, Throwable cause) {
    super(message, cause);
  }

  protected RemoteServiceException(Throwable cause) {
    super(cause);
  }

  protected RemoteServiceException(ErrorInfo errorInfo) {
    super(errorInfo);
  }

  protected RemoteServiceException(ErrorCode ErrorCode) {
    super(ErrorCode);
  }

  protected RemoteServiceException(ErrorCode ErrorCode, Object... args) {
    super(ErrorCode, args);
  }

  public ProjectModule projectModule() {
    return SystemProjectModule.INSTANCE;
  }
}
