package com.github.lalifeier.mall.cloud.common.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lalifeier.mall.cloud.common.model.WebLog;
import com.github.lalifeier.mall.cloud.common.utils.WebUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Aspect
@Component
public class WebLogAspect {

  @Autowired
  private ObjectMapper objectMapper;

//  private final ThreadLocal<WebLog> THREAD_LOCAL = new ThreadLocal<>();

  @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
  public void webLog() {
  }


  @Around("webLog()")
  public Object around(ProceedingJoinPoint pjp) throws Throwable {

    WebLog webLog = new WebLog();
    long beginTime = System.currentTimeMillis();
    HttpServletRequest request = WebUtil.getRequest();
    try {
      webLog.setRequestTime(LocalDateTime.now());
      webLog.setProtocol(request.getProtocol());
      webLog.setSchema(request.getScheme());
      webLog.setIp(request.getRemoteAddr());
      webLog.setUserAgent(request.getHeader("User-Agent"));
      webLog.setRefer(request.getHeader("Referer"));
      webLog.setSessionId(request.getSession().getId());
      webLog.setUrl(request.getRequestURL().toString());
      webLog.setHttpMethod(request.getMethod());
      webLog.setClassMethod(
        String.format(
          "%s.%s",
          pjp.getSignature().getDeclaringTypeName(),
          pjp.getSignature().getName()));
      webLog.setHeader(WebUtil.getRequestHeaders(request));
      webLog.setQuery(WebUtil.getRequestQuery(request));
//      webLog.setPayload(WebUtil.getRequestPayload(request));

      Object returnValue = pjp.proceed();

      webLog.setResponse(objectMapper.writeValueAsString(returnValue));

      return returnValue;

    } catch (Exception e) {
      webLog.setException(e.getMessage());

      throw e;
    } finally {
      webLog.setTimeCost(System.currentTimeMillis() - beginTime);

      log.info(objectMapper.writeValueAsString(webLog));
    }
  }

//  @Before("webLog()")
//  public void doBefore(JoinPoint joinPoint) {
//    HttpServletRequest request = WebUtil.getRequest();
//    WebLog webLog = new WebLog();
//    webLog.setRequestTime(LocalDateTime.now());
//    webLog.setProtocol(request.getProtocol());
//    webLog.setSchema(request.getScheme());
//    webLog.setIp(request.getRemoteAddr());
//    webLog.setUserAgent(request.getHeader("User-Agent"));
//    webLog.setSessionId(request.getSession().getId());
//    webLog.setUrl(request.getRequestURL().toString());
//    webLog.setHttpMethod(request.getMethod());
//    webLog.setClassMethod(
//      String.format(
//        "%s.%s",
//        joinPoint.getSignature().getDeclaringTypeName(),
//        joinPoint.getSignature().getName()));
//    webLog.setHeader(WebUtil.getRequestHeaders(request));
//    webLog.setQuery(WebUtil.getRequestQuery(request));
//    webLog.setPayload(WebUtil.getRequestPayload(request));
//    THREAD_LOCAL.set(webLog);
//  }
//
//  @AfterReturning(value = "webLog()", returning = "ret")
//  public void doAfterReturning(Object ret) throws Throwable {
//    WebLog webLog = THREAD_LOCAL.get();
//    webLog.setTimeCost(
//      System.currentTimeMillis()
//        - webLog
//        .getRequestTime()
//        .atZone(ZoneId.systemDefault())
//        .toInstant()
//        .toEpochMilli());
//    webLog.setResponse(objectMapper.writeValueAsString(ret));
//    log.info(objectMapper.writeValueAsString(webLog));
//  }
//
//  @AfterThrowing(pointcut = "webLog()", throwing = "e")
//  public void doAfterThrow(JoinPoint joinPoint, Throwable e) throws Throwable {
//    WebLog webLog = THREAD_LOCAL.get();
//    webLog.setTimeCost(
//      System.currentTimeMillis()
//        - webLog
//        .getRequestTime()
//        .atZone(ZoneId.systemDefault())
//        .toInstant()
//        .toEpochMilli());
//    webLog.setException(e.getMessage());
//    log.info(objectMapper.writeValueAsString(webLog));
//  }


}
