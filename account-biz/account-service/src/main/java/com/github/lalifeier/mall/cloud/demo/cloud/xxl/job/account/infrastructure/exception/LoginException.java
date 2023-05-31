package com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.account.infrastructure.exception;

import com.github.lalifeier.mall.cloud.common.exception.BaseException;
import com.github.lalifeier.mall.cloud.common.manager.ErrorInfo;
import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.api.ProjectModule;

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
