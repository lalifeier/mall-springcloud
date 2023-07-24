package com.github.lalifeier.mall.cloud.common.exception;


import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import org.slf4j.helpers.MessageFormatter;

public abstract class BaseException extends RuntimeException {

  /**
   * 错误码
   */
  private final ErrorCode errorCode;

  public BaseException(Throwable cause) {
    super(cause);
    this.errorCode = defaultErrorCode();
  }

  public BaseException(String message) {
    super(message);
    this.errorCode = defaultErrorCode();
  }

  public BaseException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }

  public BaseException(ErrorCode errorCode, Object... args) {
    super(MessageFormatter.arrayFormat(errorCode.getMessage(), args).getMessage());
    this.errorCode = errorCode;
  }

  public abstract ErrorCode defaultErrorCode();

  /**
   * 获取错误码
   *
   * @return 错误码
   */
  public ErrorCode getErrorCode() {
    return errorCode;
  }
}
