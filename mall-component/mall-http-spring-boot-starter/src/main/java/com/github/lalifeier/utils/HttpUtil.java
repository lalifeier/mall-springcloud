package com.github.lalifeier.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class HttpUtil {

    private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);

    private static class SingletonRestTemplate {
        private static final RestTemplate INSTANCE = new RestTemplate();
    }

    private HttpUtil() {
    }

    public static RestTemplate getInstance() {
        return SingletonRestTemplate.INSTANCE;
    }

    public static <T> T get(String url, Class<T> responseType) {
        return invoke(url, HttpMethod.GET, null, null, null, responseType);
    }

    public static <T> T post(String url, Class<T> responseType) {
        return invoke(url, HttpMethod.POST, null, null, null, responseType);
    }

    public static <T, E> T post(String url, E data, Class<T> responseType) {
        return invoke(url, HttpMethod.POST, data, null, null, responseType);
    }

    public static <T> T post(String url, MultiValueMap<String, String> data, Class<T> responseType) {
        return invoke(url, HttpMethod.POST, data, MediaType.APPLICATION_FORM_URLENCODED, null, responseType);
    }

    public static <T> T put(String url, Class<T> responseType) {
        return invoke(url, HttpMethod.DELETE, null, null, null, responseType);
    }

    public static <T, E> T put(String url, E data, Class<T> responseType) {
        return invoke(url, HttpMethod.PUT, data, null, null, responseType);
    }

    public static <T> T delete(String url, Class<T> responseType) {
        return invoke(url, HttpMethod.DELETE, null, null, null, responseType);
    }

    public static <T, E> T delete(String url, E data, Class<T> responseType) {
        return invoke(url, HttpMethod.DELETE, data, null, null, responseType);
    }

    public static <T, E> T invoke(String url, HttpMethod method, E data, MediaType contentType, Map<String, String> headerMap, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        contentType = contentType == null ? MediaType.APPLICATION_JSON : contentType;
        headers.setContentType(contentType);
        if (headerMap != null && headerMap.size() > 0) {
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                headers.add(entry.getKey(), entry.getValue());
            }
        }
        HttpEntity<E> httpEntity = new HttpEntity<>(data, headers);

        ResponseEntity<T> responseEntity = HttpUtil.getInstance().exchange(url, method, httpEntity, responseType);
        return responseEntity.getBody();
    }
}
