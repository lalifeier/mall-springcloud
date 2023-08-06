package com.github.lalifeier.mall.cloud.common.exception;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.enums.HttpErrorCodeEnum;

public class ValidationException extends SystemException {

  public ValidationException(Throwable cause) {
    super(cause);
  }

  public ValidationException(String message) {
    super(message);
  }

  public ValidationException(ErrorCode errorCode) {
    super(errorCode);
  }

  public ValidationException(ErrorCode errorCode, Object... args) {
    super(errorCode, args);
  }

  @Override
  public ErrorCode defaultErrorCode() {
    return HttpErrorCodeEnum.BAD_REQUEST;
  }
}
