package com.github.lalifeier.mall.cloud.auth.infrastructure.error;


import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.api.ProjectModule;
import com.github.lalifeier.mall.cloud.common.exception.BaseException;

public class LoginException extends BaseException {

  protected LoginException(String message) {
    super(message);
  }

  protected LoginException(String message, Throwable cause) {
    super(message, cause);
  }

  protected LoginException(Throwable cause) {
    super(cause);
  }

  protected LoginException(ErrorInfo errorInfo) {
    super(errorInfo);
  }

  protected LoginException(ErrorCode errorCode) {
    super(errorCode);
  }

  protected LoginException(ErrorCode errorCode, Object... args) {
    super(errorCode, args);
  }

  @Override
  public ProjectModule projectModule() {
    return UserProjectCodes.LOGIN;
  }
}
