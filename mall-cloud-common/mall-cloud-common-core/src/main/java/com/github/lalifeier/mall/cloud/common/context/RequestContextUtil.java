package com.github.lalifeier.mall.cloud.common.context;

import java.util.Map;

public class RequestContextUtil {

  private static final RequestContext requestContext = RequestContextHolder.getRequestContext();

  public static String getSessionId() {
    return (requestContext != null) ? requestContext.getSessionId() : null;
  }

  public static String getRequestMethod() {
    return (requestContext != null) ? requestContext.getRequestMethod() : null;
  }

  public static String getRequestUri() {
    return (requestContext != null) ? requestContext.getRequestUri() : null;
  }

  public static String getClientIp() {
    return (requestContext != null) ? requestContext.getClientIp() : null;
  }

  public static String getUserAgent() {
    return (requestContext != null) ? requestContext.getUserAgent() : null;
  }

  public static String getReferer() {
    return (requestContext != null) ? requestContext.getReferer() : null;
  }

  public static Map<String, String> getHeaders() {
    return (requestContext != null) ? requestContext.getHeaders() : null;
  }

  public static String getQuery() {
    return (requestContext != null) ? requestContext.getQuery() : null;
  }

  public static String getPayload() {
    return (requestContext != null) ? requestContext.getPayload() : null;
  }
}
