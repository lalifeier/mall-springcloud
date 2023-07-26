package com.github.lalifeier.mall.cloud.common.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lalifeier.mall.cloud.common.utils.WebUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;


@Slf4j
@Aspect
@Component
public class WebLogAspect {

  private final ObjectMapper objectMapper = new ObjectMapper();

  private final ThreadLocal<RequestInfo> THREAD_LOCAL = new ThreadLocal<>();

  @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
  public void webLog() {
  }

  @Before("webLog()")
  public void doBefore(JoinPoint joinPoint) throws IOException {
    long startTime = System.currentTimeMillis();
    HttpServletRequest request = WebUtil.getRequest();
    RequestInfo requestInfo = new RequestInfo();
    requestInfo.setRequestTime(Instant.ofEpochMilli(startTime)
      .atZone(ZoneOffset.UTC)
      .toLocalDateTime());
    requestInfo.setProtocol(request.getProtocol());
    requestInfo.setSchema(request.getScheme());
    requestInfo.setIp(request.getRemoteAddr());
    requestInfo.setSessionId(request.getSession().getId());
    requestInfo.setUrl(request.getRequestURL().toString());
    requestInfo.setHttpMethod(request.getMethod());
    requestInfo.setClassMethod(String.format("%s.%s", joinPoint.getSignature().getDeclaringTypeName(),
      joinPoint.getSignature().getName()));
    requestInfo.setHeader(WebUtil.getRequestHeaders(request));
    requestInfo.setQueryString(request.getQueryString());
    requestInfo.setRequestPayload(getRequestPayload(request));
    THREAD_LOCAL.set(requestInfo);
  }

  @AfterReturning(value = "webLog()", returning = "ret")
  public void doAfterReturning(Object ret) throws Throwable {
    RequestInfo requestInfo = THREAD_LOCAL.get();
    requestInfo.setTimeCost(System.currentTimeMillis() - requestInfo.getRequestTime().toInstant(ZoneOffset.UTC).toEpochMilli());
    log.info(requestInfo.toString());
  }

  @AfterThrowing(pointcut = "webLog()", throwing = "e")
  public void doAfterThrow(JoinPoint joinPoint, Throwable e) throws Throwable {
    RequestInfo requestInfo = THREAD_LOCAL.get();
    requestInfo.setTimeCost(System.currentTimeMillis() - requestInfo.getRequestTime().toInstant(ZoneOffset.UTC).toEpochMilli());
    log.info(requestInfo.toString());
  }

  private String getRequestPayload(HttpServletRequest request) throws IOException {
    String requestBody = "";

    if (request.getMethod().equals("POST") || request.getMethod().equals("PUT")) {
      requestBody = StreamUtils.copyToString(request.getInputStream(), Charset.defaultCharset());
    }

    return requestBody;
  }

  @Data
  public class RequestInfo {
    private LocalDateTime requestTime;
    private String classMethod;
    private String httpMethod;
    private String protocol;
    private String schema;
    private String ip;
    private String sessionId;
    private String url;
    private Map<String, String> header;
    private String queryString;
    private String requestPayload;
    private Long timeCost;

    @Override
    public String toString() {
      return "RequestInfo{" +
        "requestTime=" + requestTime +
        ", classMethod='" + classMethod + '\'' +
        ", httpMethod='" + httpMethod + '\'' +
        ", protocol='" + protocol + '\'' +
        ", schema='" + schema + '\'' +
        ", ip='" + ip + '\'' +
        ", sessionId='" + sessionId + '\'' +
        ", url='" + url + '\'' +
        ", header=" + header +
        ", queryString='" + queryString + '\'' +
        ", requestPayload='" + requestPayload + '\'' +
        ", timeCost=" + timeCost +
        '}';
    }
  }
}
