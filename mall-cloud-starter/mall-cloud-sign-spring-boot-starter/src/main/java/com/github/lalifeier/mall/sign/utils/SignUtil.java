package com.github.lalifeier.mall.sign.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

public class SignUtil {

    private static final String KEY = "自定义";
    private static final Logger log = LoggerFactory.getLogger(SignUtil.class);

    public static String signature(String timestamp, String nonce, String requestPayload, HttpServletRequest request) {
        Assert.isTrue(StringUtils.isNotBlank(timestamp), "timestamp can not be empty");
        Assert.isTrue(StringUtils.isNotBlank(nonce), "nonce can not be empty");
        Assert.isTrue(StringUtils.isNotBlank(requestPayload), "requestPayload can not be empty");

        Map<String, Object> params = new HashMap<>(16);
        Enumeration<String> enumeration = request.getParameterNames();
        if (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = request.getParameter(name);
            params.put(name, value);
        }

        String paramString = sortQueryParamString(params);
        String qs = String.format("%s&timestamp=%s&nonce=%s&key=%s", paramString, timestamp, nonce, KEY);

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
