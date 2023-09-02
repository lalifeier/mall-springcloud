package com.github.lalifeier.mall.cloud.common.exception;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.system.SystemErrorCode;

public class RemoteServiceException extends BaseException {

  public RemoteServiceException(Throwable cause) {
    super(cause);
  }

  public RemoteServiceException(String description) {
    super(description);
  }

  public RemoteServiceException(int code, String description) {
    super(code, description);
  }

  public RemoteServiceException(ErrorCode errorCode) {
    super(errorCode);
  }

  public RemoteServiceException(ErrorCode errorCode, Object... args) {
    super(errorCode, args);
  }

  @Override
  public ErrorCode defaultErrorCode() {
    return SystemErrorCode.REMOTE_SERVER_ERROR;
  }
}
