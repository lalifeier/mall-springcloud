package com.github.lalifeier.mall.cloud.encryptbody.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lalifeier.mall.cloud.common.result.Result;
import com.github.lalifeier.mall.cloud.common.utils.AESUtil;
import com.github.lalifeier.mall.cloud.common.utils.RSAUtil;
import com.github.lalifeier.mall.cloud.encryptbody.annotation.EncryptResponse;
import com.github.lalifeier.mall.cloud.encryptbody.config.EncryptBodyConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
@Order(999)
public class EncryptResponseBodyAdvice implements ResponseBodyAdvice<Object> {

  private final EncryptBodyConfig config;

  private final ObjectMapper objectMapper = new ObjectMapper();

  public EncryptResponseBodyAdvice(EncryptBodyConfig config) {
    this.config = config;
  }

  @Override
  public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
    if (!config.getEnable()) {
      return false;
    }

    if (returnType.hasMethodAnnotation(EncryptResponse.class)) {
      return true;
    }

    Class<?> declaringClass = returnType.getDeclaringClass();
    return declaringClass.isAnnotationPresent(EncryptResponse.class);
  }


  public String encryptData(String data, String publicKey) throws Exception {
    String aesKey = AESUtil.generateKeyString();
    String encryptData = AESUtil.encrypt(data, aesKey);
    String key = RSAUtil.encrypt(aesKey, publicKey);

    Map<String, String> encryptedData = new HashMap<>();
    encryptedData.put("data", encryptData);
    encryptedData.put("key", key);

    return objectMapper.writeValueAsString(encryptedData);
  }


  @Override
  public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
    if (body == null) {
      return null;
    }

    //try {
    //  final byte[] decryptedBytes = objectMapper.writeValueAsBytes(body);
    //  return RSAUtil.encryptString(new String(decryptedBytes), config.getPublicKey());
    //} catch (JsonProcessingException e) {
    //  log.error("Error while encrypting the response body", e);
    //}

    try {

      String publicKey = config.getPublicKey();

      if (body instanceof Result) {
        Object data = ((Result<?>) body).getData();
        String encryptData = encryptData((String) data, publicKey);
        ((Result<String>) body).setData(encryptData);
        return body;
      } else {
        return encryptData((String) body, publicKey);
      }
    } catch (Exception e) {
      log.error("Failed to encrypt the response body", e);
    }

    return null;
  }
}
