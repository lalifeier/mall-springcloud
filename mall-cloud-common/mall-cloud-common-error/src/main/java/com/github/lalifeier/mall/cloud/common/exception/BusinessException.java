package com.github.lalifeier.mall.cloud.common.exception;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.api.ProjectModule;
import com.github.lalifeier.mall.cloud.common.system.SystemErrorCode;
import com.github.lalifeier.mall.cloud.common.system.SystemProjectModule;

public class BusinessException extends BaseException {

  public BusinessException(Throwable cause) {
    super(cause);
  }

  public BusinessException(String description) {
    super(description);
  }

  public BusinessException(int code, String description) {
    super(code, description);
  }

  public BusinessException(ErrorCode errorCode) {
    super(errorCode);
  }

  public BusinessException(ErrorCode errorCode, Object... args) {
    super(errorCode, args);
  }

  @Override
  public ProjectModule projectModule() {
    return SystemProjectModule.INSTANCE;
  }

  @Override
  public ErrorCode defaultErrorCode() {
    return SystemErrorCode.BizException;
  }
}

