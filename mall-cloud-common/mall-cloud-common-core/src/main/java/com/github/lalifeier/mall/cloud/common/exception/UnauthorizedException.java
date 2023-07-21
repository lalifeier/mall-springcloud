package com.github.lalifeier.mall.cloud.common.exception;

public class UnauthorizedException extends SystemException {


  public UnauthorizedException(Throwable cause) {
    super(cause);
  }

  public UnauthorizedException(String message) {
    super(message);
  }

  public UnauthorizedException(ErrorCode errorCode) {
    super(errorCode);
  }

  public UnauthorizedException(ErrorCode errorCode, Object... args) {
    super(errorCode, args);
  }

  @Override
  public ErrorCode defaultErrorCode() {
    return ErrorCodeEnum.UNAUTHORIZED;
  }
}
