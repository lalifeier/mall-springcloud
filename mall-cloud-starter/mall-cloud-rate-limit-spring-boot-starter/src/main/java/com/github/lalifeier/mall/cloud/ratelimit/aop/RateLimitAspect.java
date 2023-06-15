package com.github.lalifeier.mall.cloud.ratelimit.aop;

import com.github.lalifeier.mall.cloud.ratelimit.annotation.RateLimit;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Aspect
@Component
public class RateLimitAspect {

  private final RedisTemplate<String, Object> redisTemplate;
  private final DefaultRedisScript<Long> limitScript;

  private final ConcurrentHashMap<String, RateLimiter> EXISTED_RATE_LIMITERS = new ConcurrentHashMap<>();

  public RateLimitAspect(RedisTemplate<String, Object> redisTemplate, DefaultRedisScript<Long> limitScript) {
    this.redisTemplate = redisTemplate;
    this.limitScript = limitScript;
  }


  @Pointcut("@annotation(rateLimit)")
  public void rateLimitPointcut(RateLimit rateLimit) {
  }

  @Around("rateLimitPointcut(rateLimit)")
  public Object around(ProceedingJoinPoint point, RateLimit rateLimit) throws Throwable {
    MethodSignature signature = (MethodSignature) point.getSignature();
    Method method = signature.getMethod();
    log.info("当前限流方法:" + method.toGenericString());

    int maxCount = rateLimit.maxCount();
    long timeout = rateLimit.time();
    String combineKey = rateLimit.limitType().generateCombinedKey(rateLimit);

    Long currentCount = executeLimitScript(combineKey, maxCount, timeout);
    log.info("限制请求:{}, 当前请求次数:{}, 缓存key:{}", combineKey, currentCount, rateLimit.key());

    if (currentCount.intValue() > maxCount) {
      throw new Exception("");
    }


    //// get rate limiter
    //RateLimiter rateLimiter = EXISTED_RATE_LIMITERS.computeIfAbsent(method.getName(), k -> RateLimiter.create(annotation.limit()));
    //
    //// process
    //if (rateLimiter!=null && rateLimiter.tryAcquire()) {
    //  return point.proceed();
    //} else {
    //  throw new RuntimeException("too many requests, please try again later...");
    //}


    return point.proceed();
  }

  private Long executeLimitScript(String key, int limit, long timeout) {
    List<String> keys = new ArrayList<>();
    keys.add(key);
    return redisTemplate.execute(limitScript, keys, limit, timeout);
  }
}
