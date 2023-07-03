package com.github.lalifeier.mall.cloud.ratelimiter.aop;

import com.github.lalifeier.mall.cloud.common.exception.http.TooManyRequestsException;
import com.github.lalifeier.mall.cloud.common.utils.WebUtil;
import com.github.lalifeier.mall.cloud.ratelimiter.annotation.RateLimiter;
import com.github.lalifeier.mall.cloud.ratelimiter.enums.LimitTypeEnum;
import com.github.lalifeier.mall.cloud.ratelimiter.executor.RedisRateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class RateLimiterAspect {
  private final RedisRateLimiter redisRateLimiter;

  public RateLimiterAspect(RedisRateLimiter redisRateLimiter) {
    this.redisRateLimiter = redisRateLimiter;
  }

  @Pointcut("@annotation(rateLimiter)")
  public void rateLimiterPointcut(RateLimiter rateLimiter) {
  }

  @Around("rateLimiterPointcut(rateLimiter)")
  public Object around(ProceedingJoinPoint point, RateLimiter rateLimiter) throws Throwable {
    MethodSignature signature = (MethodSignature) point.getSignature();
    Method method = signature.getMethod();
    log.info("当前限流方法:" + method.toGenericString());

    LimitTypeEnum limitType = rateLimiter.limitType();
    String key = getKey(point, limitType);

    if (redisRateLimiter.acquire(key, rateLimiter)) {
      return point.proceed();
    } else {
      throw new TooManyRequestsException(rateLimiter.message());
    }
  }

  private String getKey(JoinPoint point, LimitTypeEnum limitType) {
    String key = "";
    switch (limitType) {
      case GLOBAL:
        key = ((Class) point.getTarget()).getName() + ":" + ((MethodSignature) point.getSignature()).getMethod().getName();
        break;
      case IP:
        key = WebUtil.getIP();
        break;
      case USER:
//        Long userId = SecurityUtil.getUserId();
//        if (userId == null) {
//        }
//        key = String.valueOf(userId);
        break;
      default:
        key = "";
    }

    return key;
  }
}
