package com.github.lalifeier.mall.cloud.trace.interceptor;

import com.github.lalifeier.mall.cloud.common.constant.HeaderConstants;
import com.github.lalifeier.mall.cloud.common.utils.MDCTraceUtil;
import com.github.lalifeier.mall.cloud.trace.properties.TraceProperties;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * feign拦截器，传递traceId
 */
@Component
@ConditionalOnClass(value = {RequestInterceptor.class})
public class FeignTraceInterceptor implements RequestInterceptor {

  @Resource
  private TraceProperties traceProperties;

  @Override
  public void apply(RequestTemplate requestTemplate) {
    if (!traceProperties.getEnable()) {
      return;
    }

    String traceId = MDCTraceUtil.getTraceId();
    requestTemplate.header(HeaderConstants.TRACE_ID, traceId);
  }
}

