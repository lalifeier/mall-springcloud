package com.github.lalifeier.mall.cloud.ratelimit.enums;

public enum LimitType {
  /**
   * 默认策略
   * 根据方法名全局限流
   */
  GLOBAL,

  /**
   * 根据IP限流
   */
  IP,

  /**
   * 根据用户限流
   */
  USER,


  /**
   * 自定义
   */
  CUSTOM;
}
