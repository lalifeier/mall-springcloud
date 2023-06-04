package com.github.lalifeier.mall.cloud.sign.utils;

import com.github.lalifeier.mall.cloud.sign.constant.SignConstant;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

public class SignUtil {

  private static final Logger log = LoggerFactory.getLogger(SignUtil.class);

  public static String signature(String requestPayload, HttpServletRequest request) {
    Map<String, Object> params = new HashMap<>(16);
    Enumeration<String> enumeration = request.getParameterNames();
    if (enumeration.hasMoreElements()) {
      String name = enumeration.nextElement();
      String value = request.getParameter(name);
      params.put(name, value);
    }
    params.remove(SignConstant.SIGN);
    String paramString = sortQueryParamString(params);

    log.info("paramString:{} requestPayload:{}", paramString, requestPayload);
    String sign = generateSign(paramString, requestPayload);
    log.info("sign:{}", sign);

    return sign;
  }

  private static String generateSign(String paramString, String requestPayload) {
    String sign = DigestUtils.md5Hex(paramString + requestPayload).toUpperCase();
    return sign;
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
