package com.github.lalifeier.mall.cloud.account.infrastructure.exception;


import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.exception.BusinessException;

public class LoginException extends BusinessException {

  public LoginException(Throwable cause) {
    super(cause);
  }

  public LoginException(String message) {
    super(message);
  }

  public LoginException(ErrorCode errorCode) {
    super(errorCode);
  }

  public LoginException(ErrorCode errorCode, Object... args) {
    super(errorCode, args);
  }
}
