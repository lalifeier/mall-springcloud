package com.github.lalifeier.mall.cloud.common.exception;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.enums.HttpErrorCodeEnum;

public class BadRequestException extends SystemException {

  public BadRequestException(Throwable cause) {
    super(cause);
  }

  public BadRequestException(String message) {
    super(message);
  }

  public BadRequestException(ErrorCode errorCode) {
    super(errorCode);
  }

  public BadRequestException(ErrorCode errorCode, Object... args) {
    super(errorCode, args);
  }

  @Override
  public ErrorCode defaultErrorCode() {
    return HttpErrorCodeEnum.BAD_REQUEST;
  }
}
