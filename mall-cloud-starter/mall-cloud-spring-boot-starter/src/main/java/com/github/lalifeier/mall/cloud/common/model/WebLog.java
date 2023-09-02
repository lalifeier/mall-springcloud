package com.github.lalifeier.mall.cloud.common.model;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.Data;

@Data
public class WebLog {
  private LocalDateTime requestTime;
  private String classMethod;
  private String httpMethod;
  private String protocol;
  private String schema;
  private String url;
  private String uri;
  private String ip;
  private String userAgent;
  private String refer;
  private String sessionId;
  private String cookies;
  private Map<String, String> requestHeaders;
  private Map<String, String> responseHeaders;
  private String query;
  private String requestBody;
  private Long spendTime;
  private int statusCode;
  private int requestSize;
  private int responseSize;
  private String requestId;
  private String userId;
  private String responseBody;
  private String exception;
}
