package com.github.lalifeier.mall.cloud.common.filter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@ConditionalOnClass(value = {HttpServletRequest.class, OncePerRequestFilter.class})
@Order(value = -1)
public class WebTraceFilter extends OncePerRequestFilter {
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//    String traceId = request.getHeader(HeaderConstant.TRACE_ID);
//    if (StringUtils.isEmpty(traceId)) {
//      MDC.put(Constants.TRACE_ID, TraceUtil.getTraceId());
//    } else {
//      MDC.put(Constants.TRACE_ID, traceId);
//    }
    filterChain.doFilter(request, response);
  }
}
