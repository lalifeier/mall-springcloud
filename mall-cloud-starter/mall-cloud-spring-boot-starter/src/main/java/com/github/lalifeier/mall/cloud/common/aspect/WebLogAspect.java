package com.github.lalifeier.mall.cloud.common.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lalifeier.mall.cloud.common.model.WebLog;
import com.github.lalifeier.mall.cloud.common.utils.MDCTraceUtil;
import com.github.lalifeier.mall.cloud.common.utils.WebUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class WebLogAspect {
    @Autowired private ObjectMapper objectMapper;

    private final Logger log = LoggerFactory.getLogger("apiLog");

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void webLog() {}

    @Around("webLog()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {

        WebLog webLog = new WebLog();
        long beginTime = System.currentTimeMillis();
        HttpServletRequest request = WebUtil.getRequest();
        try {
            webLog.setRequestTime(LocalDateTime.now());
            webLog.setRequestId(MDCTraceUtil.getTraceId());
            webLog.setProtocol(request.getProtocol());
            webLog.setSchema(request.getScheme());
            webLog.setIp(request.getRemoteAddr());
            webLog.setUserAgent(request.getHeader("User-Agent"));
            webLog.setRefer(request.getHeader("Referer"));
            webLog.setSessionId(request.getSession().getId());
            webLog.setCookies(WebUtil.getCookies(request));
            webLog.setUri(request.getRequestURI());
            webLog.setUrl(request.getRequestURL().toString());
            webLog.setHttpMethod(request.getMethod());
            webLog.setClassMethod(
                    String.format(
                            "%s.%s",
                            pjp.getSignature().getDeclaringTypeName(),
                            pjp.getSignature().getName()));
            webLog.setRequestHeaders(WebUtil.getRequestHeaders(request));
            webLog.setQuery(request.getQueryString());

            webLog.setRequestBody(WebUtil.getRequestBody(request));
            webLog.setRequestSize(request.getContentLength());

            Object returnValue = pjp.proceed();

            HttpServletResponse response = WebUtil.getResponse();

            webLog.setStatusCode(response.getStatus());
            webLog.setResponseHeaders(WebUtil.getResponseHeaders(response));

            webLog.setResponseBody(objectMapper.writeValueAsString(returnValue));

            return returnValue;
        } catch (Exception e) {
            webLog.setException(e.getMessage());

            throw e;
        } finally {
            webLog.setSpendTime(System.currentTimeMillis() - beginTime);

            log.info(objectMapper.writeValueAsString(webLog));
        }
    }
}
