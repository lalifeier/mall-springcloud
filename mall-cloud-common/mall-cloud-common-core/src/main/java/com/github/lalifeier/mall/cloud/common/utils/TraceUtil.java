package com.github.lalifeier.mall.cloud.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;

import java.util.UUID;

public class TraceUtil {
  @Trace
  public static String getTraceId() {
    String traceId = TraceContext.traceId();
    if (StringUtils.isBlank(traceId)) {
      traceId = UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
    return traceId;
  }
}
