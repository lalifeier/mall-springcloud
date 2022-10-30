package com.github.lalifeier.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lalifeier.annotation.IgnoreResponseAdvice;
import com.github.lalifeier.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

@Slf4j
@RestControllerAdvice(basePackages = "com.github.lalifeier")
public class RestResponseHandler implements ResponseBodyAdvice<Object> {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        if (returnType.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        if (Objects.requireNonNull(returnType.getMethod()).isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }

        return true;
    }

    @Override
    @Trace
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        String traceId = TraceContext.traceId();

        if (body == null) {
            return Result.success();
        }

        if (body instanceof String) {
            try {
                response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
                return objectMapper.writeValueAsString(Result.success(body));
            } catch (JsonProcessingException e) {
                log.error(e.getMessage(), e);
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        if (body instanceof Result) {
            return (Result) body;
        }

        return Result.success(body);
    }
}
