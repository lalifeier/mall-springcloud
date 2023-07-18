package com.github.lalifeier.mall.cloud.common.error;

import lombok.Getter;

@Getter
public enum ErrorCodeEnum {
  SUCCESS(200, 200, "成功");
  private final int httpCode;
  private final int code;
  private final String description;
  private final String message;

  ErrorCodeEnum(int httpCode, int code, String description) {
    this.httpCode = httpCode;
    this.code = code;
    this.description = description;
    this.message = description;
  }

  ErrorCodeEnum(int httpCode, int code, String description, String message) {
    this.httpCode = httpCode;
    this.code = code;
    this.description = description;
    this.message = message;
  }

  /**
   * 转换为ErrorCode (自定义返回消息)
   *
   * @return
   */
  public ErrorCode convert(String message) {
    return ErrorCode.builder().
      httpCode(httpCode()).
      code(code()).
      status(name()).
      description(description()).
      message(message).
      build();
  }

  /**
   * 转换为ErrorCode
   *
   * @return
   */
  public ErrorCode convert() {
    return ErrorCode.builder().
      httpCode(httpCode()).
      code(code()).
      status(name()).
      description(description()).
      message(message()).
      build();
  }

  public int httpCode() {
    return this.httpCode;
  }

  public int code() {
    return this.code;
  }

  public String description() {
    return this.description;
  }

  public String message() {
    return this.message;
  }
}
