package com.github.lalifeier.mall.cloud.enums.context;

import java.util.HashMap;
import java.util.Map;

public class EnumContext {
  private Map<String, Class<?>> enumMap;

  public EnumContext() {
    this.enumMap = new HashMap<>();
  }

  public void registerEnum(String name, Class<?> enumClass) {
    enumMap.put(name, enumClass);
  }

  public Class<?> getEnum(String name) {
    return enumMap.get(name);
  }
}
