package com.github.lalifeier.mall.cloud.account.infrastructure.exception;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.api.ProjectModule;
import com.github.lalifeier.mall.cloud.common.exception.BaseException;
import com.google.rpc.ErrorInfo;

public class RegisterException extends BaseException {

  public RegisterException(String message) {
    super(message);
  }

  public RegisterException(String message, Throwable cause) {
    super(message, cause);
  }

  public RegisterException(Throwable cause) {
    super(cause);
  }

  public RegisterException(ErrorInfo errorInfo) {
    super(errorInfo);
  }

  public RegisterException(ErrorCode errorCode) {
    super(errorCode);
  }

  public RegisterException(ErrorCode errorCode, Object... args) {
    super(errorCode, args);
  }

  @Override
  public ProjectModule projectModule() {
    return AccountProjectCodes.REGISTER;
  }
}
