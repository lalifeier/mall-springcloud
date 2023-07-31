package com.github.lalifeier.mall.cloud.common.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lalifeier.mall.cloud.common.utils.WebUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

@Slf4j
@Aspect
@Component
public class WebLogAspect {

  @Autowired
  private ObjectMapper objectMapper;

  private final ThreadLocal<WebLog> THREAD_LOCAL = new ThreadLocal<>();

  @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
  public void webLog() {
  }

  @Before("webLog()")
  public void doBefore(JoinPoint joinPoint) {
    HttpServletRequest request = WebUtil.getRequest();
    WebLog webLog = new WebLog();
    webLog.setRequestTime(LocalDateTime.now());
    webLog.setProtocol(request.getProtocol());
    webLog.setSchema(request.getScheme());
    webLog.setIp(request.getRemoteAddr());
    webLog.setUserAgent(request.getHeader("User-Agent"));
    webLog.setSessionId(request.getSession().getId());
    webLog.setUrl(request.getRequestURL().toString());
    webLog.setHttpMethod(request.getMethod());
    webLog.setClassMethod(
      String.format(
        "%s.%s",
        joinPoint.getSignature().getDeclaringTypeName(),
        joinPoint.getSignature().getName()));
    webLog.setHeader(WebUtil.getRequestHeaders(request));
    webLog.setQuery(WebUtil.getRequestQuery(request));
    webLog.setPayload(WebUtil.getRequestPayload(request));
    THREAD_LOCAL.set(webLog);
  }

  @AfterReturning(value = "webLog()", returning = "ret")
  public void doAfterReturning(Object ret) throws Throwable {
    WebLog webLog = THREAD_LOCAL.get();
    webLog.setTimeCost(
      System.currentTimeMillis()
        - webLog
        .getRequestTime()
        .atZone(ZoneId.systemDefault())
        .toInstant()
        .toEpochMilli());
    webLog.setResponseInfo(objectMapper.writeValueAsString(ret));
    log.info(objectMapper.writeValueAsString(webLog));
  }

  @AfterThrowing(pointcut = "webLog()", throwing = "e")
  public void doAfterThrow(JoinPoint joinPoint, Throwable e) throws Throwable {
    WebLog webLog = THREAD_LOCAL.get();
    webLog.setTimeCost(
      System.currentTimeMillis()
        - webLog
        .getRequestTime()
        .atZone(ZoneId.systemDefault())
        .toInstant()
        .toEpochMilli());
    webLog.setExceptionInfo(e.getMessage());
    log.info(objectMapper.writeValueAsString(webLog));
  }

  @Data
  public class WebLog {
    //    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime requestTime;
    private String classMethod;
    private String httpMethod;
    private String protocol;
    private String schema;
    private String ip;
    private String userAgent;
    private String sessionId;
    private String url;
    private Map<String, String> header;
    private Map<String, String> query;
    private String payload;
    private Long timeCost;
    private String responseInfo;
    private String exceptionInfo;
  }
}
