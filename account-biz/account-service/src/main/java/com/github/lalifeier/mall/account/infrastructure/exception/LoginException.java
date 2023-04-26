package com.github.lalifeier.mall.account.infrastructure.exception;

import com.github.lalifeier.mall.common.error.api.ErrorCode;
import com.github.lalifeier.mall.common.error.api.ProjectModule;
import com.github.lalifeier.mall.common.error.exception.BaseException;
import com.github.lalifeier.mall.common.error.manager.ErrorInfo;

public class LoginException extends BaseException {

  public LoginException(String message) {
    super(message);
  }

  public LoginException(String message, Throwable cause) {
    super(message, cause);
  }

  public LoginException(Throwable cause) {
    super(cause);
  }

  public LoginException(ErrorInfo errorInfo) {
    super(errorInfo);
  }

  public LoginException(ErrorCode errorCode) {
    super(errorCode);
  }

  public LoginException(ErrorCode errorCode, Object... args) {
    super(errorCode, args);
  }

  @Override
  public ProjectModule projectModule() {
    return AccountProjectCodes.LOGIN;
  }
}
