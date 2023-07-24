package com.github.lalifeier.mall.cloud.common.exception;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.constant.ErrorCodeEnum;

public class SystemException extends BaseException {

  public SystemException(Throwable cause) {
    super(cause);
  }

  public SystemException(String message) {
    super(message);
  }

  public SystemException(ErrorCode errorCode) {
    super(errorCode);
  }

  public SystemException(ErrorCode errorCode, Object... args) {
    super(errorCode, args);
  }

  @Override
  public ErrorCode defaultErrorCode() {
    return ErrorCodeEnum.SYSTEM_EXCEPTION;
  }
}
