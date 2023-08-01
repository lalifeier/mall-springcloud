package com.github.lalifeier.mall.cloud.common.context.request;

import lombok.Data;

import java.util.Map;

@Data
public class RequestContext {
  private final String traceId;
  private final String sessionId;
  private final String requestMethod;
  private final String requestUri;
  private final String clientIp;
  private final String userAgent;
  private final String referer;
  private final Map<String, String> headers;
  private final String query;
  private final String payload;
}
