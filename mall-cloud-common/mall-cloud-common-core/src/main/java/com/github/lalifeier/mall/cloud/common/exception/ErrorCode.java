package com.github.lalifeier.mall.cloud.common.exception;

public interface ErrorCode {

  /**
   * 获取http状态码
   */
  int getHttpCode();

  /**
   * 获取错误码
   */
  int getCode();

  /**
   * 获取错误状态
   */
  String getStatus();

  /**
   * 获取异常信息
   */
  String getDescription();

  /**
   * 获取返回给用户的异常消息
   */
  String getMessage();
}

