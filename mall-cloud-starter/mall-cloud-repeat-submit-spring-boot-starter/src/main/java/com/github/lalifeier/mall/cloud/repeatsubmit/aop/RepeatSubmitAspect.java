package com.github.lalifeier.mall.cloud.repeatsubmit.aop;

import com.github.lalifeier.mall.cloud.repeatsubmit.annotation.RepeatSubmit;
import com.github.lalifeier.mall.cloud.repeatsubmit.enums.RepeatSubmitType;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Aspect
@Component
public class RepeatSubmitAspect {
    private final RedisTemplate<String, Object> redisTemplate;

    private static final ThreadLocal<String> paramKeyThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<String> tokenKeyThreadLocal = new ThreadLocal<>();

    public RepeatSubmitAspect(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Pointcut("@annotation(repeatSubmit)")
    public void repeatSubmitPointcut(RepeatSubmit repeatSubmit) {}

    @Around("repeatSubmitPointcut(repeatSubmit)")
    public Object around(ProceedingJoinPoint point, RepeatSubmit repeatSubmit) throws Throwable {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                        .getRequest();

        String key;
        if (repeatSubmit.type() == RepeatSubmitType.PARAM) {
            key = paramKeyThreadLocal.get();
            if (key == null) {
                key = generateParamKey(request);
                paramKeyThreadLocal.set(key);
            }
        } else {
            key = tokenKeyThreadLocal.get();
            if (key == null) {
                key = generateTokenKey(request);
                tokenKeyThreadLocal.set(key);
            }
        }

        if (redisTemplate.hasKey(key)) {
            log.warn("Repeat submission detected for key {}", key);
            throw new RuntimeException(repeatSubmit.message());
        }

        redisTemplate.opsForValue().set(key, key, repeatSubmit.interval(), TimeUnit.MILLISECONDS);
        try {
            return point.proceed();
        } finally {
            redisTemplate.delete(key);
            if (repeatSubmit.type() == RepeatSubmitType.PARAM) {
                paramKeyThreadLocal.remove();
            } else {
                tokenKeyThreadLocal.remove();
            }
        }
    }

    private String generateTokenKey(HttpServletRequest request) {
        String token = request.getHeader("x-token");
        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Token is empty");
        }
        return token;
    }

    private String generateParamKey(HttpServletRequest request) throws IOException {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return generateIdentifier(
                request.getRequestURI(),
                request.getParameterMap(),
                request.getReader().lines().collect(Collectors.joining(System.lineSeparator())),
                ipAddress);
    }

    private static String generateIdentifier(
            String url, Map<String, String[]> params, String body, String ipAddress) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(url.getBytes());
            md.update(params.toString().getBytes());
            md.update(body.getBytes());
            md.update(ipAddress.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to generate request identifier", e);
        }
    }
}
