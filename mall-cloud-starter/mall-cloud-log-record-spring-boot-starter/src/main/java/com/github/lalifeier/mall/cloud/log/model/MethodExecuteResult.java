package com.github.lalifeier.mall.cloud.log.model;


import lombok.Data;

import java.lang.reflect.Method;

@Data
public class MethodExecuteResult {
  private boolean success;
  private Throwable throwable;
  private String errorMessage;
  private Object result;
  private final Method method;
  private final Object[] args;
  private final Class<?> targetClass;

  public MethodExecuteResult(Method method, Object[] args, Class<?> targetClass) {
    this.method = method;
    this.args = args;
    this.targetClass = targetClass;
  }
}
