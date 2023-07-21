package com.github.lalifeier.mall.cloud.common.exception;

public class ForbiddenException extends SystemException {


  public ForbiddenException(Throwable cause) {
    super(cause);
  }

  public ForbiddenException(String message) {
    super(message);
  }

  public ForbiddenException(ErrorCode errorCode) {
    super(errorCode);
  }

  public ForbiddenException(ErrorCode errorCode, Object... args) {
    super(errorCode, args);
  }

  @Override
  public ErrorCode defaultErrorCode() {
    return ErrorCodeEnum.FORBIDDEN;
  }
}
