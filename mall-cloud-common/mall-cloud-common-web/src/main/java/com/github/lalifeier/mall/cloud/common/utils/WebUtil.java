package com.github.lalifeier.mall.cloud.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

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

}
