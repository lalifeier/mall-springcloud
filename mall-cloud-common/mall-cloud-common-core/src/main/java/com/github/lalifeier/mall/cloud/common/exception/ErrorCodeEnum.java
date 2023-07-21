package com.github.lalifeier.mall.cloud.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCodeEnum implements ErrorCode {
  SUCCESS(200, "成功"),
  UN_KNOW(500, "未知异常"),
  SYSTEM_EXCEPTION(500, "系统异常"),
  BUSINESS_EXCEPTION(500, "业务异常"),
  BAD_REQUEST(400, "错误的请求"),
  UNAUTHORIZED(401, "未授权"),
  FORBIDDEN(403, "禁止访问"),
  NOT_FOUND(404, "未找到"),
  TOO_MANY_REQUESTS(429, "请求过多"),
  SERVER_ERROR(500, "服务器内部错误"),
  REMOTE_SERVER_ERROR(500, "远程调用错误"),
  SERVICE_UNAVAILABLE(503, "服务不可用");
  private final int httpCode;
  private final int code;
  private final String description;
  private final String message;

  ErrorCodeEnum(int httpCode, String description) {
    this.httpCode = httpCode;
    this.code = httpCode;
    this.description = description;
    this.message = description;
  }

  ErrorCodeEnum(int httpCode, String description, String message) {
    this.httpCode = httpCode;
    this.code = httpCode;
    this.description = description;
    this.message = message;
  }

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

  @Override
  public String getStatus() {
    return name();
  }
}
