package com.github.lalifeier.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Nullable;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class HttpUtil {

    private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);

    @Resource
    private RestTemplate restTemplate;

    private static HttpUtil httpUtil;

    @PostConstruct
    public void init() {
        httpUtil = this;
        httpUtil.restTemplate = this.restTemplate;
    }

    public static <T> T get(String url, @Nullable HttpEntity<?> requestEntity, Class<T> responseType) {
        return invoke(url, HttpMethod.GET, requestEntity, responseType);
    }

    public static <T> T post(String url, @Nullable HttpEntity<?> requestEntity, Class<T> responseType) {
        return invoke(url, HttpMethod.POST, requestEntity, responseType);
    }

    public static <T> T invoke(String url, HttpMethod method, @Nullable HttpEntity<?> requestEntity, Class<T> responseType, Object... uriVariables) {
        ResponseEntity<T> result = httpUtil.restTemplate.exchange(url, method, requestEntity, responseType, uriVariables);
        return result.getBody();
    }
}
