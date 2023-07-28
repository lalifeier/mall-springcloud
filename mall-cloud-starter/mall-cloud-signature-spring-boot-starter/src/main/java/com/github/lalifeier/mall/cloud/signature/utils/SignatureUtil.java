package com.github.lalifeier.mall.cloud.signature.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

public class SignatureUtil {

  private static final Logger log = LoggerFactory.getLogger(SignatureUtil.class);

  public static String signature(String requestPayload, HttpServletRequest request) {
    String method = request.getMethod();
    String url = request.getRequestURL().toString();
    String queryString = getQueryString(request);
    return generateSignature(method, url, queryString, requestPayload);
  }

  public static String generateSignature(String method, String url, String queryString, String requestPayload) {
    String payloadHash = DigestUtils.md5Hex(requestPayload);
    StringBuilder messageBuilder = new StringBuilder();
    messageBuilder.append(method).append("\n")
      .append(url).append("\n")
      .append(queryString).append("\n")
      .append(payloadHash);
    String message = messageBuilder.toString();
    log.info("message:{}", message);
    String signature = DigestUtils.sha256Hex(message);
    log.info("signature:{}", signature);
    return signature;
  }

  private static String getHeaders(Map<String, String> headers) {
    List<String> headerStrings = new ArrayList<>();
    for (Map.Entry<String, String> entry : headers.entrySet()) {
      headerStrings.add(entry.getKey().toLowerCase() + ":" + entry.getValue().trim());
    }
    Collections.sort(headerStrings);
    return String.join("\n", headerStrings);
  }

  private static String getQueryString(HttpServletRequest request) {
    Map<String, Object> params = new HashMap<>(16);
    Enumeration<String> enumeration = request.getParameterNames();
    if (enumeration.hasMoreElements()) {
      String name = enumeration.nextElement();
      String value = request.getParameter(name);
      params.put(name, value);
    }
    return sortQueryParamString(params);
  }

  /**
   * 按照字母顺序进行升序排序
   *
   * @param params 请求参数 注意请求参数中不能包含key
   * @return 排序后结果
   */
  private static String sortQueryParamString(Map<String, Object> params) {
    if (params == null) {
      return null;
    }

    List<String> listKeys = new ArrayList<>(params.keySet());
    List<String> paramStr = listKeys.stream().sorted(Comparator.naturalOrder())
      .map(name -> {
        try {
          return String.format("%s=%s", name, URLEncoder.encode(params.get(name).toString(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
          throw new RuntimeException(e);
        }
      })
      .collect(Collectors.toList());

    return String.join("&", paramStr);
  }
}

