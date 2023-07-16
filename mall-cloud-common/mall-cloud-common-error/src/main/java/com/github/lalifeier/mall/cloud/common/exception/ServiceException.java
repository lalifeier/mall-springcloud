package com.github.lalifeier.mall.cloud.common.exception;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.api.ProjectModule;
import com.github.lalifeier.mall.cloud.common.system.SystemErrorCode;
import com.github.lalifeier.mall.cloud.common.system.SystemProjectModule;

public class ServiceException extends BaseException {


  public ServiceException(Throwable cause) {
    super(cause);
  }

  public ServiceException(String description) {
    super(description);
  }

  public ServiceException(int code, String description) {
    super(code, description);
  }

  public ServiceException(ErrorCode errorCode) {
    super(errorCode);
  }

  public ServiceException(ErrorCode errorCode, Object... args) {
    super(errorCode, args);
  }

  @Override
  public ProjectModule projectModule() {
    return SystemProjectModule.INSTANCE;
  }

  @Override
  public ErrorCode defaultErrorCode() {
    return SystemErrorCode.SERVER_ERROR;
  }
}
