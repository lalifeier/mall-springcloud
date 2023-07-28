package com.github.lalifeier.mall.cloud.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class WebUtil {
  private static final String UN_KNOWN = "unknown";
  private static Gson gson = new GsonBuilder().serializeNulls().create();

  public static HttpServletRequest getRequest() {
    ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    return requestAttributes.getRequest();
  }

  public static HttpServletResponse getResponse() {
    ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    return requestAttributes.getResponse();
  }

  /**
   * 获取ip
   */
  public static String getIP() {
    return getIP(WebUtil.getRequest());
  }

  public static String getIP(HttpServletRequest request) {
    String ip = request.getHeader("x-forwarded-for");
    if (StringUtils.isBlank(ip) || UN_KNOWN.equalsIgnoreCase(ip)) {
      ip = request.getHeader("X-Forwarded-For");
    }
    if (StringUtils.isBlank(ip) || UN_KNOWN.equalsIgnoreCase(ip)) {
      ip = request.getHeader("Proxy-Client-IP");
    }
    if (StringUtils.isBlank(ip) || UN_KNOWN.equalsIgnoreCase(ip)) {
      ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (StringUtils.isBlank(ip) || UN_KNOWN.equalsIgnoreCase(ip)) {
      ip = request.getHeader("HTTP_CLIENT_IP");
    }
    if (StringUtils.isBlank(ip) || UN_KNOWN.equalsIgnoreCase(ip)) {
      ip = request.getHeader("HTTP_X_FORWARDED_FOR");
    }
    if (StringUtils.isBlank(ip) || UN_KNOWN.equalsIgnoreCase(ip)) {
      ip = request.getRemoteAddr();
    }
    return StringUtils.isBlank(ip) ? null : ip.split(",")[0];
  }

  public static void writeJson(HttpServletResponse response, int status, Object object) {
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setStatus(status);
    PrintWriter out = null;
    try {
      String data = object instanceof String ? (String) object : gson.toJson(object);
      out = response.getWriter();
      out.print(data);
      out.flush();
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    } finally {
      if (out != null) {
        out.close();
      }
    }
  }


  public static void writeJson(HttpServletResponse response, Object object) {
    writeJson(response, HttpServletResponse.SC_OK, object);
  }

  public static void writeJson(ServletResponse response, Object object) {
    if (response instanceof HttpServletResponse) {
      writeJson((HttpServletResponse) response, object);
    }
  }

  public static String getSessionId(HttpServletRequest request) {
    return request.getSession().getId();
  }

  public static Map<String, String> getRequestHeaders(HttpServletRequest request) {
    Map<String, String> headerMap = new HashMap<>();

    Enumeration<String> headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String headerName = headerNames.nextElement();
      String headerValue = request.getHeader(headerName);

      if (!org.springframework.util.StringUtils.isEmpty(headerName) && !org.springframework.util.StringUtils.isEmpty(headerValue)) {
        headerMap.put(headerName, headerValue);
      }
    }

    return headerMap;
  }

  public static Map<String, String> getRequestQuery(HttpServletRequest request) {
    Map<String, String> queryMap = new HashMap<>();
    Enumeration<String> parameterNames = request.getParameterNames();
    while (parameterNames.hasMoreElements()) {
      String parameterName = parameterNames.nextElement();
      String parameterValue = request.getParameter(parameterName);
      queryMap.put(parameterName, parameterValue);
    }
    return queryMap;
  }

  public static String getRequestPayload(HttpServletRequest request) {
    StringBuilder payload = new StringBuilder();
    try (BufferedReader reader = request.getReader()) {
      String line;
      while ((line = reader.readLine()) != null) {
        payload.append(line);
      }
    } catch (IOException e) {
      // 处理异常
      e.printStackTrace();
    }
    return payload.toString();
  }


}
