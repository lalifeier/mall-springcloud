package com.github.lalifeier.mall.cloud.log.model;

import java.lang.reflect.Method;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MethodInfo {
  private Class<?> clazz;
  private Method method;
  private Object[] args;
}
