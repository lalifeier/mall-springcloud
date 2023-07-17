package com.github.lalifeier.mall.cloud.common.exception;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.system.SystemErrorCode;

public class ValidationException extends BaseException {

  public ValidationException(Throwable cause) {
    super(cause);
  }

  public ValidationException(String description) {
    super(description);
  }

  public ValidationException(int code, String description) {
    super(code, description);
  }

  public ValidationException(ErrorCode errorCode) {
    super(errorCode);
  }

  public ValidationException(ErrorCode errorCode, Object... args) {
    super(errorCode, args);
  }

  @Override
  public ErrorCode defaultErrorCode() {
    return SystemErrorCode.BAD_REQUEST;
  }
}
