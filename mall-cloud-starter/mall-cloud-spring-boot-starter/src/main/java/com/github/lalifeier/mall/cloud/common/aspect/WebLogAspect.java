package com.github.lalifeier.mall.cloud.common.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lalifeier.mall.cloud.common.utils.WebUtil;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class WebLogAspect {

    @Autowired private ObjectMapper objectMapper;

    private final ThreadLocal<RequestInfo> THREAD_LOCAL = new ThreadLocal<>();

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void webLog() {}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws IOException {
        HttpServletRequest request = WebUtil.getRequest();
        RequestInfo requestInfo = new RequestInfo();
        requestInfo.setRequestTime(LocalDateTime.now());
        requestInfo.setProtocol(request.getProtocol());
        requestInfo.setSchema(request.getScheme());
        requestInfo.setIp(request.getRemoteAddr());
        requestInfo.setSessionId(request.getSession().getId());
        requestInfo.setUrl(request.getRequestURL().toString());
        requestInfo.setHttpMethod(request.getMethod());
        requestInfo.setClassMethod(
                String.format(
                        "%s.%s",
                        joinPoint.getSignature().getDeclaringTypeName(),
                        joinPoint.getSignature().getName()));
        requestInfo.setHeader(WebUtil.getRequestHeaders(request));
        requestInfo.setQuery(WebUtil.getRequestQuery(request));
        requestInfo.setPayload(WebUtil.getRequestPayload(request));
        THREAD_LOCAL.set(requestInfo);
    }

    @AfterReturning(value = "webLog()", returning = "ret")
    public void doAfterReturning(Object ret) throws Throwable {
        RequestInfo requestInfo = THREAD_LOCAL.get();
        requestInfo.setTimeCost(
                System.currentTimeMillis()
                        - requestInfo
                                .getRequestTime()
                                .atZone(ZoneId.systemDefault())
                                .toInstant()
                                .toEpochMilli());
        log.info(objectMapper.writeValueAsString(requestInfo));
    }

    @AfterThrowing(pointcut = "webLog()", throwing = "e")
    public void doAfterThrow(JoinPoint joinPoint, Throwable e) throws Throwable {
        RequestInfo requestInfo = THREAD_LOCAL.get();
        requestInfo.setTimeCost(
                System.currentTimeMillis()
                        - requestInfo
                                .getRequestTime()
                                .atZone(ZoneId.systemDefault())
                                .toInstant()
                                .toEpochMilli());
        log.info(objectMapper.writeValueAsString(requestInfo));
    }

    @Data
    public class RequestInfo {
        //    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime requestTime;
        private String classMethod;
        private String httpMethod;
        private String protocol;
        private String schema;
        private String ip;
        private String sessionId;
        private String url;
        private Map<String, String> header;
        private Map<String, String> query;
        private String payload;
        private Long timeCost;
    }
}
