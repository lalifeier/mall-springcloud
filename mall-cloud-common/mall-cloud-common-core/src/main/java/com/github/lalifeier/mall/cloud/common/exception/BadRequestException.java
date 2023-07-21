package com.github.lalifeier.mall.cloud.common.exception;


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
    return ErrorCodeEnum.BAD_REQUEST;
  }
}
