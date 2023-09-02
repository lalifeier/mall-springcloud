package com.github.lalifeier.mall.cloud.common.factory;

import com.github.lalifeier.mall.cloud.common.exception.BusinessException;

public class ExceptionFactory {

  public static BusinessException businessException(String errorMessage) {
    return new BusinessException(errorMessage);
  }

  public static BusinessException businessException(int errorCode, String errorMessage) {
    return new BusinessException(errorCode, errorMessage);
  }
}
