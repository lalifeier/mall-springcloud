package com.github.lalifeier.mall.cloud.ratelimit.aop;

import com.github.lalifeier.mall.cloud.common.utils.WebUtil;
import com.github.lalifeier.mall.cloud.ratelimit.annotation.RateLimit;
import com.github.lalifeier.mall.cloud.ratelimit.enums.LimitType;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Collections;
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

    String prefix = rateLimit.prefix();
    LimitType limitType = rateLimit.limitType();
    String key = getKey(point, limitType, prefix, rateLimit.key());

    int capacity = rateLimit.capacity();
    int rate = rateLimit.rate();

//    log.info("限制请求:{}, 当前请求次数:{}, 缓存key:{}", combineKey, currentCount, rateLimit.key());

    if (tryAcquire(key, capacity, rate)) {
      return point.proceed();
    } else {
      throw new RuntimeException("too many requests, please try again later...");
    }
  }


  /**
   * 尝试获取令牌并进行限流处理
   *
   * @param key             令牌桶键名
   * @param bucketCapacity  令牌桶容量
   * @param tokensPerSecond 每秒生成的令牌数目
   * @return 如果获取到令牌则返回true，否则返回false
   */
  public boolean tryAcquire(String key, int bucketCapacity, double tokensPerSecond) {
    List<String> keys = Collections.singletonList(key);
    Long result = redisTemplate.execute(limitScript, keys, bucketCapacity, tokensPerSecond);

    return (result != null && result == 1L);
  }

  private String getKey(JoinPoint point, LimitType limitType, String prefix, String originKey) {
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
      case CUSTOM:
//        key = String.valueOf(resolve(point, originKey));
        break;
      default:
        key = "";
    }

//    if (StringUtils.isBlank(key)) {
//      ExceptionUtil.rethrowClientSideException("Key不能为空");
//    }

    return prefix + key;
  }
}
