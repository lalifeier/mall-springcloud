package com.github.lalifeier.mall.cloud.common.api;


import com.github.lalifeier.mall.cloud.common.manager.ErrorInfo;

public interface ErrorCodeException {
  /**
   * 错误信息
   */
  ErrorInfo getErrorInfo();

  /**
   * 服务+模块信息
   */
  ProjectModule projectModule();
}
