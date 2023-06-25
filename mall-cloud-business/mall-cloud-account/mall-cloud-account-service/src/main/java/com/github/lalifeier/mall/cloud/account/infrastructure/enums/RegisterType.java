package com.github.lalifeier.mall.cloud.account.infrastructure.enums;

public enum RegisterType {
  USERNAME,
  EMAIL,
  PHONE;

  public static RegisterType parse(String registerType) {
    try {
      return RegisterType.valueOf(registerType.toUpperCase());
    } catch (IllegalArgumentException e) {
      throw new RuntimeException("不支持此注册方式：" + registerType);
    }
  }
}
