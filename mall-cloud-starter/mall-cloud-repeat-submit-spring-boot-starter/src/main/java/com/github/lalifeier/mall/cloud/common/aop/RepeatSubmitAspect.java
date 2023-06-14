package com.github.lalifeier.mall.cloud.common.aop;

import com.github.lalifeier.mall.cloud.common.annotation.RepeatSubmit;
import com.github.lalifeier.mall.cloud.common.constant.RedisKeyConstants;
import com.github.lalifeier.mall.cloud.common.enums.RepeatSubmitType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@Aspect
@Component
public class RepeatSubmitAspect {
  private final RedisTemplate<String, Object> redisTemplate;

  public RepeatSubmitAspect(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  @Pointcut("@annotation(repeatSubmit)")
  public void repeatSubmitPointcut(RepeatSubmit repeatSubmit) {
  }

  @Around("repeatSubmitPointcut(repeatSubmit)")
  public Object around(ProceedingJoinPoint point, RepeatSubmit repeatSubmit) throws Throwable {
    String key = generateKey(repeatSubmit);
    if (redisTemplate.hasKey(key)) {
      throw new RuntimeException(repeatSubmit.message());
    }

    redisTemplate.opsForValue().set(key, key, repeatSubmit.interval(), TimeUnit.MILLISECONDS);
    try {
      return point.proceed();
    } finally {
      redisTemplate.delete(key);
    }
  }

  private String generateKey(RepeatSubmit repeatSubmit) {
    if (repeatSubmit.type() == RepeatSubmitType.PARAM) {
      return String.format(RedisKeyConstants.SUBMIT_PARAM_KEY, getParam());
    } else {
      return String.format(RedisKeyConstants.SUBMIT_TOKEN_KEY, getToken());
    }
  }

  private String getParam() {
    StringBuilder sb = new StringBuilder();
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    sb.append(request.getRemoteAddr()).append("|");
    sb.append(request.getRequestURI()).append("|");
    Map<String, String[]> paramMap = request.getParameterMap();
    List<String> keys = new ArrayList<>(paramMap.keySet());
    Collections.sort(keys);
    for (String key : keys) {
      sb.append(key).append(":");
      String[] values = paramMap.get(key);
      if (values != null && values.length > 0) {
        sb.append(values[0]);
      }
      sb.append(",");
    }
    return DigestUtils.md5DigestAsHex(sb.toString().getBytes());
  }

  private String getToken() {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    return Optional.ofNullable(request.getHeader("x-token")).orElse("");
  }
}
