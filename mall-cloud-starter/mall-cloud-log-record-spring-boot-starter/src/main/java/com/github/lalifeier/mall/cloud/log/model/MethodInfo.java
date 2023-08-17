package com.github.lalifeier.mall.cloud.log.model;

import lombok.Builder;
import lombok.Data;

import java.lang.reflect.Method;


@Data
@Builder
public class MethodInfo {
  private Class<?> clazz;
  private Method method;
  private Object[] args;
}
