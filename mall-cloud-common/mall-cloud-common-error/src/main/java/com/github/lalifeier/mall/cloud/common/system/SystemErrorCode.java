package com.github.lalifeier.mall.cloud.common.system;

import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.manager.ErrorManager;
import lombok.Getter;

@Getter
public enum SystemErrorCode implements ErrorCode {
  SUCCESS(200, "成功"), SYSTEM_EXCEPTION(500, "系统异常"), BUSINESS_EXCEPTION(500, "业务异常"), BAD_REQUEST(
      400, "错误的请求"), UNAUTHORIZED(401, "未授权"), FORBIDDEN(403, "禁止访问"), NOT_FOUND(404,
          "未找到"), TOO_MANY_REQUESTS(429, "请求过多"), SERVER_ERROR(500,
              "服务器内部错误"), REMOTE_SERVER_ERROR(500, "远程调用错误"), SERVICE_UNAVAILABLE(503, "服务不可用");

  private final int nodeNum;
  private final String description;
  private final String message;

  SystemErrorCode(int nodeNum, String description) {
    this.nodeNum = nodeNum;
    this.description = description;
    this.message = description;
    ErrorManager.register(SystemProjectModule.INSTANCE, this);
  }

  SystemErrorCode(int nodeNum, String description, String message) {
    this.nodeNum = nodeNum;
    this.description = description;
    this.message = message;
    ErrorManager.register(SystemProjectModule.INSTANCE, this);
  }
}
