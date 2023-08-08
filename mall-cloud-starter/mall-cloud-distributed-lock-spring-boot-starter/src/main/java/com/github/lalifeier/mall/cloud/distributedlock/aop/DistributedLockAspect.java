package com.github.lalifeier.mall.cloud.distributedlock.aop;

import com.github.lalifeier.mall.cloud.distributedlock.annotation.DistributedLock;
import com.github.lalifeier.mall.cloud.distributedlock.enums.LockTypeEnum;
import com.github.lalifeier.mall.cloud.distributedlock.exception.LockTimeoutException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Slf4j
@Aspect
@Component
public class DistributedLockAspect {
  private ExpressionParser parser = new SpelExpressionParser();
  private ParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();

  @Resource
  private RedissonClient redissonClient;

  @Pointcut("@annotation(distributedLock)")
  public void distributedLockPointcut(DistributedLock distributedLock) {
  }

  @Around("distributedLockPointcut(distributedLock)")
  public Object around(ProceedingJoinPoint pjp, DistributedLock distributedLock) throws Throwable {
    Method method = ((MethodSignature) pjp.getSignature()).getMethod();
    Object[] args = pjp.getArgs();
    String lockKey = parse(distributedLock.field(), distributedLock.prefix(), method, args);

    LockTypeEnum lockType = distributedLock.lockType();
    String methodName = method.getName();
    long waitTime = distributedLock.waitTime();
    long leaseTime = distributedLock.leaseTime();
    TimeUnit unit = distributedLock.unit();

    RLock rLock = getLock(lockType, lockKey);

    boolean lockAcquired = false;

    try {
      String waitTimeString = toDurationString(waitTime, unit);
      String leaseTimeString = toDurationString(leaseTime, unit);

      log.info("Attempting to acquire lock '{}' of type {} for method {}. waitTime={}, leaseTime={}", lockKey, lockType, methodName, waitTimeString, leaseTimeString);
      if (waitTime > 0) {
        lockAcquired = rLock.tryLock(waitTime, leaseTime, unit);
        if (!lockAcquired) {
          log.warn("Failed to acquire lock '{}' of type {} for method {}. waitTime={}, leaseTime={}", lockKey, lockType, methodName, waitTimeString, leaseTimeString);
          throw new LockTimeoutException("获取锁等待超时");
        } else {
          log.info("Lock '{}' of type {} acquired for method {}. waitTime={}, leaseTime={}", lockKey, lockType, methodName, waitTimeString, leaseTimeString);
        }
      } else {
        rLock.lock(leaseTime, unit);
        log.info("Lock '{}' of type {} acquired for method {}. waitTime={}, leaseTime={}", lockKey, lockType, methodName, waitTimeString, leaseTimeString);
      }

      return pjp.proceed();
    } catch (LockTimeoutException e) {
      log.error("LockTimeoutException occurred while executing locked method", e);
      throw e;
    } catch (Exception e) {
      log.error("Exception occurred while executing locked method", e);
      throw new RuntimeException("获取锁异常");
    } finally {
      if (lockAcquired) {
        rLock.unlock();
        log.info("Lock '{}' of type {} released for method {}", lockKey, lockType, methodName);
      }
    }
  }

  private String toDurationString(long duration, TimeUnit unit) {
    return duration + " " + unit.toString().toLowerCase();
  }


  /**
   * 解析spring EL表达式
   *
   * @param keyExpression
   * @param method
   * @param args
   * @return
   */
  private String parse(String keyExpression, String prefix, Method method, Object[] args) {
    String[] params = discoverer.getParameterNames(method);
    EvaluationContext context = new StandardEvaluationContext();
    for (int i = 0; i < params.length; i++) {
      context.setVariable(params[i], args[i]);
    }

    if (prefix == null || prefix.isEmpty()) {
      prefix = "";
    } else {
      String split = ":";
      if (!prefix.endsWith(split)) {
        prefix = prefix + split;
      }
    }

    return prefix + parser.parseExpression(keyExpression).getValue(context, String.class);
  }

  RLock getLock(LockTypeEnum lockType, String lockKey) {
    RLock rLock = null;
    switch (lockType) {
      case FAIR_LOCK:
        rLock = redissonClient.getFairLock(lockKey);
        break;
      case REENTRANT_LOCK:
        rLock = redissonClient.getLock(lockKey);
        break;
      case READ_LOCK:
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock(lockKey);
        rLock = readWriteLock.readLock();
        break;
      case WRITE_LOCK:
        RReadWriteLock rwLock = redissonClient.getReadWriteLock(lockKey);
        rLock = rwLock.writeLock();
        break;
      default:
        rLock = redissonClient.getLock(lockKey);
    }
    return rLock;
  }
}
