package com.github.lalifeier.mall.cloud.feign.interceptor;

import com.github.lalifeier.mall.cloud.common.constant.HeaderConstant;
import com.github.lalifeier.mall.cloud.common.utils.TraceUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class FeignInterceptor implements RequestInterceptor {
  @Override
  public void apply(RequestTemplate requestTemplate) {
    String traceId = TraceUtil.getTraceId();
    requestTemplate.header(HeaderConstant.TRACE_ID, traceId);
  }
}
