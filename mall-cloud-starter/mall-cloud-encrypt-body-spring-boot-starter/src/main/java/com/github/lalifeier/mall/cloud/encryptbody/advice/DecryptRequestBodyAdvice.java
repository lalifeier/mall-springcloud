package com.github.lalifeier.mall.cloud.encryptbody.advice;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lalifeier.mall.cloud.common.utils.AESUtil;
import com.github.lalifeier.mall.cloud.common.utils.RSAUtil;
import com.github.lalifeier.mall.cloud.encryptbody.annotation.EncryptRequest;
import com.github.lalifeier.mall.cloud.encryptbody.config.EncryptBodyConfig;
import com.github.lalifeier.mall.cloud.encryptbody.http.DecryptHttpInputMessage;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@ControllerAdvice
public class DecryptRequestBodyAdvice implements RequestBodyAdvice {
  private final EncryptBodyConfig config;

  private final ObjectMapper objectMapper = new ObjectMapper();
  ;

  public DecryptRequestBodyAdvice(EncryptBodyConfig config) {
    this.config = config;
  }

  public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
    if (!config.getEnable()) {
      return false;
    }

    if (!HttpMethod.POST.name().equals(Objects.requireNonNull(methodParameter.getMethod()).getName())) {
      return false;
    }

    if (methodParameter.hasMethodAnnotation(EncryptRequest.class)) {
      return true;
    }

    Class<?> declaringClass = methodParameter.getDeclaringClass();
    return declaringClass.isAnnotationPresent(EncryptRequest.class);
  }

  //private InputStream decryptInputMessage(HttpInputMessage inputMessage, String privateKey) throws IOException {
  //  byte[] encryptedBody = IOUtils.toByteArray(inputMessage.getBody());
  //  String decryptedBody = RSAUtil.decryptString(new String(encryptedBody), privateKey);
  //  return new ByteArrayInputStream(decryptedBody.getBytes(StandardCharsets.UTF_8));
  //}

  private InputStream decryptInputMessage(HttpInputMessage inputMessage, String privateKey) {
    try {
      JsonNode rootNode = objectMapper.readValue(inputMessage.getBody(), JsonNode.class);

      String encryptedData = rootNode.get("data").asText();
      String key = rootNode.get("key").asText();
      String aesKey = RSAUtil.decrypt(key, privateKey);
      String decryptedData = AESUtil.decrypt(encryptedData, aesKey);

      return new ByteArrayInputStream(decryptedData.getBytes(StandardCharsets.UTF_8));
    } catch (Exception e) {
      log.error("Failed to decrypt the request body", e);
      return null;
    }
  }

  @Override
  public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
    if (!MediaType.APPLICATION_JSON.isCompatibleWith(inputMessage.getHeaders().getContentType())) {
      return inputMessage;
    }

    InputStream decryptedStream = decryptInputMessage(inputMessage, config.getPrivateKey());

    return new DecryptHttpInputMessage(decryptedStream, inputMessage.getHeaders());
  }

  @Override
  public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
    return body;
  }

  @Override
  public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
    return body;
  }
}
