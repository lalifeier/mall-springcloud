package com.github.lalifeier.mall.cloud.gateway.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.Assert;

public class SignUtil {

  private static final String KEY = "自定义";
  private static final Logger log = LoggerFactory.getLogger(SignUtil.class);

  // public static String generateSign(SortedMap<String, String> sortedMap, String appSecret) {
  // sortedMap.remove(Header.SIGN);
  //
  // StringBuilder plainText = new StringBuilder();
  // for (Map.Entry<String, String> entry : sortedMap.entrySet()) {
  // plainText.append(entry.getKey() + entry.getValue());
  // }
  // String digest = plainText.toString() + appSecret;
  // return DigestUtils.md5Hex(digest).toUpperCase();
  // }

  public static String generateSign(String timestamp, String nonce, String requestPayload,
      ServerHttpRequest request) {
    Assert.isTrue(StringUtils.isNotBlank(timestamp), "timestamp can not be empty");
    Assert.isTrue(StringUtils.isNotBlank(nonce), "nonce can not be empty");
    Assert.isTrue(StringUtils.isNotBlank(requestPayload), "requestPayload can not be empty");

    Map<String, Object> params = new HashMap<>();
    request.getQueryParams().forEach((k, v) -> {
      if (null != v && v.size() > 0) {
        params.put(k, v.get(0));
      }
    });

    String paramString = sortQueryParamString(params);
    String qs =
        String.format("%s&timestamp=%s&nonce=%s&key=%s", paramString, timestamp, nonce, KEY);

    log.info("requestPayload:{} qs:{}", requestPayload, qs);
    String sign = DigestUtils.md5Hex(requestPayload + qs).toUpperCase();
    log.info("sign:{}", sign);

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
    List<String> paramStr = listKeys.stream().sorted(Comparator.naturalOrder()).map(name -> {
      try {
        return String.format("%s=%s", name,
            URLEncoder.encode(params.get(name).toString(), "UTF-8"));
      } catch (UnsupportedEncodingException e) {
        throw new RuntimeException(e);
      }
    }).collect(Collectors.toList());

    return String.join("&", paramStr);
  }
}
