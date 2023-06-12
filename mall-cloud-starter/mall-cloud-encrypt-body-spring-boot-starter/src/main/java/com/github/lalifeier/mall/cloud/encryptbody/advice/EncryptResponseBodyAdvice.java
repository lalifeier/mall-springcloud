package com.github.lalifeier.mall.cloud.encryptbody.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lalifeier.mall.cloud.common.result.Result;
import com.github.lalifeier.mall.cloud.encryptbody.annotation.EncryptResponse;
import com.github.lalifeier.mall.cloud.encryptbody.config.EncryptBodyConfig;
import com.github.lalifeier.mall.cloud.encryptbody.model.EncryptData;
import com.github.lalifeier.mall.cloud.encryptbody.util.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Slf4j
@ControllerAdvice
public class EncryptResponseBodyAdvice implements ResponseBodyAdvice<Object> {

  private final EncryptBodyConfig config;

  private final ObjectMapper objectMapper = new ObjectMapper();

  public EncryptResponseBodyAdvice(EncryptBodyConfig config) {
    this.config = config;
  }

  @Override
  public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
    System.out.println("============================EncryptResponseBodyAdvice=======================================");

    if (!config.getEnable()) {
      return false;
    }

    if (returnType.hasMethodAnnotation(EncryptResponse.class)) {
      return true;
    }

    Class<?> declaringClass = returnType.getDeclaringClass();
    return declaringClass.isAnnotationPresent(EncryptResponse.class);
  }


  @Override
  public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
    if (body == null) {
      return null;
    }

    try {
      String publicKey = config.getPublicKey();

      if (body instanceof Result) {
        Object data = ((Result<?>) body).getData();
        EncryptData encryptedData = EncryptUtil.encrypt(data.toString(), publicKey);
        ((Result<EncryptData>) body).setData(encryptedData);
        return body;
      } else {
        EncryptData encryptedData = EncryptUtil.encrypt((String) body, publicKey);
        return objectMapper.writeValueAsString(encryptedData);
      }
    } catch (Exception e) {
      log.error("Failed to encrypt the response body", e);
    }

    return null;
  }
}
