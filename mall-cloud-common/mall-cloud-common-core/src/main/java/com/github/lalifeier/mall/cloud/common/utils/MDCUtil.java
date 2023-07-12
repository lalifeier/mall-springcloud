package com.github.lalifeier.mall.cloud.common.utils;

import org.slf4j.MDC;

import java.util.Map;

public class MDCUtil {
  public static final String TRACE_ID = "traceId";

  public static final String SPAN_ID = "spanId";

  public static String getTraceId() {
    return MDC.get(TRACE_ID);
  }

  public static void setTraceId(String traceId) {
    MDC.put(TRACE_ID, traceId);
  }

  public static void setContextMap(Map<String, String> context) {
    MDC.setContextMap(context);
  }

  public static void removeTraceId() {
    MDC.remove(TRACE_ID);
  }

  public static void clear() {
    MDC.clear();
  }
}
