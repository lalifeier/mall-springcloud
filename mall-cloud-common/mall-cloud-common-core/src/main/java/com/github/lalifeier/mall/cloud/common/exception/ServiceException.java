package com.github.lalifeier.mall.cloud.common.exception;

public class ServiceException extends SystemException {


  public ServiceException(Throwable cause) {
    super(cause);
  }

  public ServiceException(String message) {
    super(message);
  }

  public ServiceException(ErrorCode errorCode) {
    super(errorCode);
  }

  public ServiceException(ErrorCode errorCode, Object... args) {
    super(errorCode, args);
  }

  @Override
  public ErrorCode defaultErrorCode() {
    return ErrorCodeEnum.SERVER_ERROR;
  }
}
