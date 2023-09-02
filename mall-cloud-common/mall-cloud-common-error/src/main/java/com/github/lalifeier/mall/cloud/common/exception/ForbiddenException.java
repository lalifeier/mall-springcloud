package com.github.lalifeier.mall.cloud.common.exception;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.system.SystemErrorCode;

public class ForbiddenException extends BaseException {

  public ForbiddenException(Throwable cause) {
    super(cause);
  }

  public ForbiddenException(String description) {
    super(description);
  }

  public ForbiddenException(int code, String description) {
    super(code, description);
  }

  public ForbiddenException(ErrorCode errorCode) {
    super(errorCode);
  }

  public ForbiddenException(ErrorCode errorCode, Object... args) {
    super(errorCode, args);
  }

  @Override
  public ErrorCode defaultErrorCode() {
    return SystemErrorCode.FORBIDDEN;
  }
}
