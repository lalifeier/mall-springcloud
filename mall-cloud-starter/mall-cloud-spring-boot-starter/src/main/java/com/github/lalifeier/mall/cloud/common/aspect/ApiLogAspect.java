package com.github.lalifeier.mall.cloud.common.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Aspect
@Component
//@Profile({})
public class ApiLogAspect {

  @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
  public void apiLog() {
  }

  @Before("apiLog()")
  public void doBefore(JoinPoint joinPoint) {
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();
    HttpSession session = request.getSession();
    String sessionId = session.getId();

    log.info("请求URL : " + request.getRequestURL());
    log.info("请求IP : " + request.getRemoteAddr());
    log.info("请求方法 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
  }
}
