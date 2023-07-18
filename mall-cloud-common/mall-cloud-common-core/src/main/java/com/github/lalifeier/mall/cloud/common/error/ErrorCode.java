package com.github.lalifeier.mall.cloud.common.error;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorCode {

  /**
   * http状态码
   */
  private int httpCode;

  /**
   * 错误码
   */
  private int code;

  /**
   * 错误状态
   */
  private String status;

  /**
   * 异常信息
   */
  private String description;

  /**
   * 返回给用户的异常消息
   */
  private String message;
}
