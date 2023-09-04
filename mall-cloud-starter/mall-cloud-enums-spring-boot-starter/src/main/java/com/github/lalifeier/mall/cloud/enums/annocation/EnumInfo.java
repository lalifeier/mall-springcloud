package com.github.lalifeier.mall.cloud.enums.annocation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 注解用于为枚举类提供信息
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumInfo {
  /**
   * 枚举的名称
   */
  String name();

  /**
   * 枚举的键
   */
  String key();

  /**
   * 枚举的描述信息
   */
  String description();
}
