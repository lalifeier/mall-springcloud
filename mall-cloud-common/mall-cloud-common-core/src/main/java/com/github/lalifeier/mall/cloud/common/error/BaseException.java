package com.github.lalifeier.mall.cloud.common.error;


public abstract class BaseException extends RuntimeException {

  /**
   * 错误码
   */
  private final ErrorCode errorCode;

  protected BaseException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }

  protected BaseException(ErrorCodeEnum errorCodeEnum) {
    this(errorCodeEnum.convert());
  }

  /**
   * 获取错误码
   *
   * @return 错误码
   */
  public ErrorCode getErrorCode() {
    return errorCode;
  }

//  public BaseException(String message) {
//    super(message);
//    this.errorCode = new ErrorCode(message);
//  }


//  public BaseException(Throwable cause) {
//    super(cause);
//  }
//
//  public BaseException(String description) {
//    super(description);
//  }
//
//  public BaseException(int code, String description) {
//    super(description);
//  }
//
//  public BaseException(ErrorCode errorCode) {
//    super(errorCode.getDescription());
//  }
//
//  public BaseException(ErrorCode errorCode, Object... args) {
//    super(MessageFormatter.arrayFormat(errorCode.getMessage(), args).getMessage());
//  }
//
//  public abstract ErrorCode defaultErrorCode();
}
