package com.github.lalifeier.mall.cloud.common.utils;

import org.slf4j.MDC;

public class MDCTraceUtil {
  public static final String TRACE_ID = "traceId";

  public static final String SPAN_ID = "spanId";

  public static void addTrace() {
    String traceId = TraceUtil.generateTraceId();
    setTraceId(traceId);
  }

  public static void putTrace(String traceId) {
    setTraceId(traceId);
  }

  public static void removeTrace() {
    MDC.remove(TRACE_ID);
  }

  public static String getTraceId() {
    return MDC.get(TRACE_ID);
  }

  public static String getSpanId() {
    return MDC.get(SPAN_ID);
  }

  public static void setTraceId(String traceId) {
    MDC.put(TRACE_ID, traceId);
  }

  public static void clear() {
    MDC.clear();
  }
}
