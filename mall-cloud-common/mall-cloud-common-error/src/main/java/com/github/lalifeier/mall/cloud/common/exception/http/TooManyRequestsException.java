package com.github.lalifeier.mall.cloud.common.exception.http;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.api.ProjectModule;
import com.github.lalifeier.mall.cloud.common.exception.BaseException;
import com.github.lalifeier.mall.cloud.common.manager.ErrorInfo;
import com.github.lalifeier.mall.cloud.common.system.SystemProjectModule;
import lombok.Getter;

public class TooManyRequestsException extends BaseException {

  @Getter
  private long limitTimestamp;

  public TooManyRequestsException(String message) {
    super(message);
  }

  public TooManyRequestsException(String message, long limitTimestamp) {
    super(message);
    this.limitTimestamp = limitTimestamp;
  }

  public TooManyRequestsException(String message, Throwable cause) {
    super(message, cause);
  }

  public TooManyRequestsException(Throwable cause) {
    super(cause);
  }

  public TooManyRequestsException(ErrorInfo errorInfo) {
    super(errorInfo);
  }

  public TooManyRequestsException(ErrorCode ErrorCode) {
    super(ErrorCode);
  }

  public TooManyRequestsException(ErrorCode ErrorCode, Object... args) {
    super(ErrorCode, args);
  }

  @Override
  public ProjectModule projectModule() {
    return SystemProjectModule.INSTANCE;
  }
}
