package com.github.lalifeier.mall.cloud.ratelimit.annotation;

import com.github.lalifeier.mall.cloud.ratelimit.enums.LimitType;

import java.lang.annotation.*;

@Inherited
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimit {

  /**
   * 限流key
   */
  String key() default "";


  /**
   * Key的前缀
   */
  String prefix() default "limiter:";


//  /**
//   * 限制请求数量，默认为10
//   */
//  int limit() default 10;
//
//
//  /**
//   * 时间窗口大小，默认为1秒钟
//   */
//  long timeout() default 1000L;

  /**
   * 令牌桶的容量，默认100
   */
  int capacity() default 100;

  /**
   * 每秒钟默认产生令牌数量，默认10个
   */
  int rate() default 10;

  /**
   * 限流条件类型
   */
  LimitType limitType() default LimitType.GLOBAL;


  /**
   * 提示消息
   */
  String message() default "您的访问过于频繁，请稍后重试";
}
