package com.github.lalifeier.mall.cloud.common.exception;


import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.api.ErrorCodeException;
import com.github.lalifeier.mall.cloud.common.api.ProjectModule;
import com.github.lalifeier.mall.cloud.common.manager.ErrorInfo;

public abstract class BaseException extends RuntimeException implements ErrorCodeException {

  private final ErrorInfo errorInfo;

  public BaseException(String message) {
    super(message);
    this.errorInfo = ErrorInfo.parse(message);
  }

  public BaseException(String message, Throwable cause) {
    super(message, cause);
    this.errorInfo = ErrorInfo.parse(message);
  }

  public BaseException(Throwable cause) {
    super(cause);
    this.errorInfo = ErrorInfo.parse(cause.getMessage());
  }

  public BaseException(ErrorInfo errorInfo) {
    super(errorInfo.toString());
    this.errorInfo = errorInfo;
  }

  public BaseException(ErrorCode errorCode) {
    this(ErrorInfo.parse(errorCode));
    ProjectModule.check(projectModule(), errorCode.projectModule());
  }

  public BaseException(ErrorCode errorCode, Object... args) {
    this(ErrorInfo.parse(errorCode, args));
    ProjectModule.check(projectModule(), errorCode.projectModule());
  }

  @Override
  public ErrorInfo getErrorInfo() {
    return errorInfo;
  }
}
