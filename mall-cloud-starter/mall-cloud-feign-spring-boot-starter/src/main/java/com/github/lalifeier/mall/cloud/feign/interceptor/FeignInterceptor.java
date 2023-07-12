package com.github.lalifeier.mall.cloud.feign.interceptor;

import com.github.lalifeier.mall.cloud.common.constant.HeaderConstants;
import com.github.lalifeier.mall.cloud.common.utils.MDCUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class FeignInterceptor implements RequestInterceptor {
  @Override
  public void apply(RequestTemplate requestTemplate) {
    String traceId = MDCUtil.getTraceId();
    requestTemplate.header(HeaderConstants.TRACE_ID, traceId);
  }
}
