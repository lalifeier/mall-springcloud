package com.github.lalifeier.mall.cloud.common.exception;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.constant.HttpErrorCodeEnum;

public class NotFoundException extends SystemException {


  public NotFoundException(Throwable cause) {
    super(cause);
  }

  public NotFoundException(String message) {
    super(message);
  }

  public NotFoundException(ErrorCode errorCode) {
    super(errorCode);
  }

  public NotFoundException(ErrorCode errorCode, Object... args) {
    super(errorCode, args);
  }

  @Override
  public ErrorCode defaultErrorCode() {
    return HttpErrorCodeEnum.NOT_FOUND;
  }
}
