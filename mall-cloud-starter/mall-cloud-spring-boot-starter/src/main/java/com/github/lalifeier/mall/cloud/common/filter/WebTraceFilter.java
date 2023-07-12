package com.github.lalifeier.mall.cloud.common.filter;

import com.github.lalifeier.mall.cloud.common.constant.HeaderConstants;
import com.github.lalifeier.mall.cloud.common.utils.MDCUtil;
import com.github.lalifeier.mall.cloud.common.utils.TraceUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.core.Ordered;
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
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class WebTraceFilter extends OncePerRequestFilter {


  private static String getTraceId(HttpServletRequest request) {
    String traceId = request.getHeader(HeaderConstants.TRACE_ID);
    if (StringUtils.isEmpty(traceId)) {
      traceId = TraceUtil.generateTraceId();
    }
    return traceId;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String traceId = getTraceId(request);

    MDCUtil.setTraceId(traceId);

    filterChain.doFilter(request, response);
  }
}
