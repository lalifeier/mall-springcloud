package com.github.lalifeier.mall.cloud.common.exception;

public class BusinessException extends BaseException {

  public BusinessException(Throwable cause) {
    super(cause);
  }

  public BusinessException(String message) {
    super(message);
  }

  public BusinessException(ErrorCode errorCode) {
    super(errorCode);
  }

  public BusinessException(ErrorCode errorCode, Object... args) {
    super(errorCode, args);
  }

  @Override
  public ErrorCode defaultErrorCode() {
    return ErrorCodeEnum.BUSINESS_EXCEPTION;
  }
}

