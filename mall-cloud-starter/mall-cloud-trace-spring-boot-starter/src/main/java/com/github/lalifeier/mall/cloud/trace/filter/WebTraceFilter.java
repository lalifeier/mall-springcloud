package com.github.lalifeier.mall.cloud.trace.filter;

import com.github.lalifeier.mall.cloud.common.constant.HeaderConstants;
import com.github.lalifeier.mall.cloud.common.utils.MDCTraceUtil;
import com.github.lalifeier.mall.cloud.trace.properties.TraceProperties;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/** web过滤器，生成日志链路追踪id，并赋值MDC */
@Component
@ConditionalOnClass(value = {HttpServletRequest.class, OncePerRequestFilter.class})
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class WebTraceFilter extends OncePerRequestFilter {

  private final TraceProperties traceProperties;

  public WebTraceFilter(TraceProperties traceProperties) {
    this.traceProperties = traceProperties;
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    return !traceProperties.getEnable();
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    try {
      String traceId = request.getHeader(HeaderConstants.TRACE_ID);
      if (StringUtils.isEmpty(traceId)) {
        MDCTraceUtil.addTrace();
      } else {
        MDCTraceUtil.putTrace(traceId);
      }

      filterChain.doFilter(request, response);
    } finally {
      MDCTraceUtil.removeTrace();
    }
  }
}
