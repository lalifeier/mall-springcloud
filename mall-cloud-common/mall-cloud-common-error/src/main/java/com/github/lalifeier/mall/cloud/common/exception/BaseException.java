package com.github.lalifeier.mall.cloud.common.exception;


import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.api.ProjectModule;
import lombok.Getter;
import org.slf4j.helpers.MessageFormatter;

public abstract class BaseException extends RuntimeException {

  /**
   * 错误码
   */
  @Getter
  private int code;

  /**
   * 异常信息
   */
  @Getter
  private String description;

  public BaseException(Throwable cause) {
    super(cause);
    this.code = defaultErrorCode().getCode();
    this.description = cause.getMessage();
  }

  public BaseException(String description) {
    super(description);
    this.code = defaultErrorCode().getCode();
    this.description = description;
  }

  public BaseException(int code, String description) {
    super(description);
    this.code = code;
    this.description = description;
  }

  public BaseException(ErrorCode errorCode) {
    super(errorCode.getDescription());
    this.code = errorCode.getCode();
    this.description = errorCode.getDescription();
    ProjectModule.check(projectModule(), errorCode.projectModule());
  }

  public BaseException(ErrorCode errorCode, Object... args) {
    super(MessageFormatter.arrayFormat(errorCode.getMessage(), args).getMessage());
    this.code = errorCode.getCode();
    this.description = errorCode.getDescription();
    ProjectModule.check(projectModule(), errorCode.projectModule());
  }

  public abstract ProjectModule projectModule();

  public abstract ErrorCode defaultErrorCode();
}
