package com.github.lalifeier.mall.cloud.account.infrastructure.exception;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.exception.BusinessException;

public class RegisterException extends BusinessException {


  public RegisterException(Throwable cause) {
    super(cause);
  }

  public RegisterException(String description) {
    super(description);
  }

  public RegisterException(int code, String description) {
    super(code, description);
  }

  public RegisterException(ErrorCode errorCode) {
    super(errorCode);
  }

  public RegisterException(ErrorCode errorCode, Object... args) {
    super(errorCode, args);
  }
}
