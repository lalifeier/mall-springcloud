package com.github.lalifeier.mall.cloud.common.system;


import com.github.lalifeier.mall.cloud.common.api.ErrorCode;
import com.github.lalifeier.mall.cloud.common.manager.ErrorManager;
import lombok.Getter;

@Getter
public enum SystemErrorCode implements ErrorCode {

  SUCCESS(0, "成功"),
  SYSTEM_ERROR(1, "系统错误"),
  REMOTE_CALL_ERROR(2, "远程调用错误"),
  UNKNOWN_ERROR(-1, "未知异常");

  private final int nodeNum;
  private final String message;

  SystemErrorCode(int nodeNum, String message) {
    this.nodeNum = nodeNum;
    this.message = message;
    ErrorManager.register(SystemProjectModule.INSTANCE, this);
  }
}
