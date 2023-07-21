package com.github.lalifeier.mall.cloud.common.exception;

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
    return ErrorCodeEnum.BAD_REQUEST;
  }
}
