package com.github.lalifeier.mall.cloud.ratelimiter.annotation;

import com.github.lalifeier.mall.cloud.ratelimiter.enums.AlgorithmTypeEnum;
import com.github.lalifeier.mall.cloud.ratelimiter.enums.LimitTypeEnum;

import java.lang.annotation.*;

@Inherited
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimiter {

  /**
   * 限流算法
   */
  AlgorithmTypeEnum algorithmType() default AlgorithmTypeEnum.TOKEN_BUCKET;

  /**
   * 令牌桶容量，默认100
   */
  int capacity() default 100;

  /**
   * 令牌生成速率，默认10个
   */
  int rate() default 10;

  /**
   * 令牌消耗速率，默认1个
   */

  int perSecond() default 1;

  /**
   * 限流条件类型
   */
  LimitTypeEnum limitType() default LimitTypeEnum.GLOBAL;
  
  /**
   * 提示消息
   */
  String message() default "您的访问过于频繁，请稍后重试";
}
