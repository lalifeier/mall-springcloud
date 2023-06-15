package com.github.lalifeier.mall.cloud.ratelimit.aop;

import com.github.lalifeier.mall.cloud.ratelimit.annotation.RateLimit;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Aspect
@Component
public class RateLimitAspect {

  @Autowired
  private RedisTemplate<String, Object> redisTemplate;

  @Autowired
  private DefaultRedisScript<Long> limitScript;

  @Before("@annotation(rateLimit)")
  public void doBefore(ProceedingJoinPoint point, RateLimit rateLimit) throws Throwable {
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
  }

  public Long executeLimitScript(String key, int limit, long timeout) {
    List<String> keys = new ArrayList<>();
    keys.add(key);
    return redisTemplate.execute(limitScript, keys, limit, timeout);
  }
}
