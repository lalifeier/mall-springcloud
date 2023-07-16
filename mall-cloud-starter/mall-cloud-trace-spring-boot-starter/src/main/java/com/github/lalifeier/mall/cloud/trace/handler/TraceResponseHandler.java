package com.github.lalifeier.mall.cloud.trace.handler;

import com.github.lalifeier.mall.cloud.common.constant.HeaderConstants;
import com.github.lalifeier.mall.cloud.common.utils.MDCTraceUtil;
import com.github.lalifeier.mall.cloud.trace.properties.TraceProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Slf4j
@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class TraceResponseHandler implements ResponseBodyAdvice<Object> {

  private final TraceProperties traceProperties;

  public TraceResponseHandler(TraceProperties traceProperties) {
    this.traceProperties = traceProperties;
  }

  @Override
  public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
    return traceProperties.getEnable();
  }

  @Override
  public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
    String traceId = MDCTraceUtil.getTraceId();
    response.getHeaders().add(HeaderConstants.TRACE_ID, traceId);
    return body;
  }
}
