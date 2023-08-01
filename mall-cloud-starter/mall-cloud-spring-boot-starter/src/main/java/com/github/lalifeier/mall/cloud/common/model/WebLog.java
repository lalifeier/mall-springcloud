package com.github.lalifeier.mall.cloud.common.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class WebLog {
  private LocalDateTime requestTime;
  private String classMethod;
  private String httpMethod;
  private String protocol;
  private String schema;
  private String ip;
  private String userAgent;
  private String refer;
  private String sessionId;
  private String url;
  private Map<String, String> header;
  private Map<String, String> query;
  private String payload;
  private Long timeCost;
  private String response;
  private String exception;
}
