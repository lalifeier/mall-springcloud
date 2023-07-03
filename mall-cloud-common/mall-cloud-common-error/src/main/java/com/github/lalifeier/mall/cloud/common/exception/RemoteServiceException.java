package com.github.lalifeier.mall.cloud.common.exception;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.api.ProjectModule;
import com.github.lalifeier.mall.cloud.common.manager.ErrorInfo;
import com.github.lalifeier.mall.cloud.common.system.SystemProjectModule;

public class RemoteServiceException extends BaseException {
  public RemoteServiceException(String message) {
    super(message);
  }

  public RemoteServiceException(String message, Throwable cause) {
    super(message, cause);
  }

  public RemoteServiceException(Throwable cause) {
    super(cause);
  }

  public RemoteServiceException(ErrorInfo errorInfo) {
    super(errorInfo);
  }

  public RemoteServiceException(ErrorCode ErrorCode) {
    super(ErrorCode);
  }

  public RemoteServiceException(ErrorCode ErrorCode, Object... args) {
    super(ErrorCode, args);
  }

  public ProjectModule projectModule() {
    return SystemProjectModule.INSTANCE;
  }
}
