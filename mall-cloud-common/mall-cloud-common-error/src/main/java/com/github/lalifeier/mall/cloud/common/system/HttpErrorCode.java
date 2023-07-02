package com.github.lalifeier.mall.cloud.common.system;


import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.manager.ErrorManager;
import lombok.Getter;

@Getter
public enum HttpErrorCode implements ErrorCode {

  OK(200, "成功"),
  BAD_REQUEST(400, "错误的请求"),
  UNAUTHORIZED(401, "未授权"),
  FORBIDDEN(403, "禁止访问"),
  NOT_FOUND(404, "未找到"),
  TOO_MANY_REQUESTS(429, "请求过多"),
  INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
  SERVICE_UNAVAILABLE(503, "服务不可用");

  private final int status;
  private final String message;

  HttpErrorCode(int status, String message) {
    this.status = status;
    this.message = message;
    ErrorManager.register(SystemProjectModule.INSTANCE, this);
  }

  @Override
  public int getNodeNum() {
    return this.status;
  }
}
