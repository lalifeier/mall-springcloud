package com.github.lalifeier.mall.cloud.encryptbody.advice;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lalifeier.mall.cloud.common.utils.EncryptBodyUtil;
import com.github.lalifeier.mall.cloud.encryptbody.annotation.EncryptRequest;
import com.github.lalifeier.mall.cloud.encryptbody.config.EncryptBodyConfig;
import com.github.lalifeier.mall.cloud.encryptbody.http.DecryptHttpInputMessage;
import jakarta.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

@Slf4j
@ControllerAdvice
public class DecryptRequestBodyAdvice implements RequestBodyAdvice {
    private final EncryptBodyConfig config;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public DecryptRequestBodyAdvice(EncryptBodyConfig config) {
        this.config = config;
    }

    public boolean supports(
            MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        System.out.println(
                "============================DecryptRequestBodyAdvice=======================================");
        if (!config.getEnable()) {
            return false;
        }

        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (!request.getMethod().equals(HttpMethod.POST.name())) {
            return false;
        }

        if (methodParameter.hasMethodAnnotation(EncryptRequest.class)) {
            return true;
        }

        Class<?> declaringClass = methodParameter.getDeclaringClass();
        return declaringClass.isAnnotationPresent(EncryptRequest.class);
    }

    @Override
    public HttpInputMessage beforeBodyRead(
            HttpInputMessage inputMessage,
            MethodParameter parameter,
            Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType) {
        if (!MediaType.APPLICATION_JSON.isCompatibleWith(
                inputMessage.getHeaders().getContentType())) {
            return inputMessage;
        }

        try {
            String privateKey = config.getPrivateKey();

            JsonNode rootNode = objectMapper.readValue(inputMessage.getBody(), JsonNode.class);
            String encryptedData = rootNode.get("data").asText();
            String key = rootNode.get("key").asText();

            String body = EncryptBodyUtil.decrypt(encryptedData, key, privateKey);

            InputStream decryptedStream = new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8));

            return new DecryptHttpInputMessage(decryptedStream, inputMessage.getHeaders());
        } catch (Exception e) {
            log.error("Failed to decrypt the request body", e);
            return null;
        }
    }

    @Override
    public Object afterBodyRead(
            Object body,
            HttpInputMessage inputMessage,
            MethodParameter parameter,
            Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public Object handleEmptyBody(
            Object body,
            HttpInputMessage inputMessage,
            MethodParameter parameter,
            Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }
}
