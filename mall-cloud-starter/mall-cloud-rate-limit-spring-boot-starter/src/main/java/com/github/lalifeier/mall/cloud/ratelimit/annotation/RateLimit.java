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
  String key() default "None";

  /**
   * 限流时间,单位秒
   */
  int interval() default 60;

  /**
   * 限流次数
   */
  int maxCount() default 100;

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
   * 限流使用的缓存类型
   */
//  CacheType cacheType() default CacheType.REDIS;
}
