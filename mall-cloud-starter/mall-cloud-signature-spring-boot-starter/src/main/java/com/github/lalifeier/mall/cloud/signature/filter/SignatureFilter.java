package com.github.lalifeier.mall.cloud.signature.filter;

import com.github.lalifeier.mall.cloud.signature.constant.SignatureConstants;
import com.github.lalifeier.mall.cloud.signature.exception.InvalidRequestTimeOrNonceException;
import com.github.lalifeier.mall.cloud.signature.exception.InvalidSignatureException;
import com.github.lalifeier.mall.cloud.signature.properties.SignatureProperties;
import com.github.lalifeier.mall.cloud.signature.utils.SignatureUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class SignatureFilter extends OncePerRequestFilter {

  private static final String SHOULD_NOT_FILTER = "SHOULD_NOT_FILTER" + CsrfFilter.class.getName();

  private AccessDeniedHandler accessDeniedHandler = new AccessDeniedHandlerImpl();

  private final RedisTemplate<String, Object> redisTemplate;

  private final SignatureProperties properties;

  public SignatureFilter(RedisTemplate<String, Object> redisTemplate, SignatureProperties properties) {
    this.redisTemplate = redisTemplate;
    this.properties = properties;
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    // 过滤不需要签名验证的地址
    Set<String> uriSet = new HashSet<>(properties.getWhitelist());
    String requestUri = request.getRequestURI();
    for (String uri : uriSet) {
      if (requestUri.contains(uri)) {
        return true;
      }
    }

    return Boolean.TRUE.equals(request.getAttribute(SHOULD_NOT_FILTER));
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    if (shouldNotFilter(request)) {
      filterChain.doFilter(request, response);
      return;
    }

    // 获取应用id
    String appId = request.getHeader(SignatureConstants.APP_ID);
    // 获取appKey
    String appKey = request.getHeader(SignatureConstants.APP_KEY);
    // 获取appSecret
    String appSecret = request.getHeader(SignatureConstants.APP_SECRET);
    // 获取时间戳
    String timestamp = request.getHeader(SignatureConstants.TIMESTAMP);
    // 获取随机字符串
    String nonce = request.getHeader(SignatureConstants.NONCE);
    // 获取签名
    String signature = request.getHeader(SignatureConstants.SIGNATURE);

    // 判断时间是否大于 xx 秒（防止重放攻击）
    long NONCE_STR_TIMEOUT_SECONDS = properties.getReplayAttackTime();
    if (StringUtils.isBlank(timestamp) || Long.parseLong(timestamp) + (1000 * NONCE_STR_TIMEOUT_SECONDS) > System.currentTimeMillis()) {
      this.logger.debug("request timestamp expired " + UrlUtils.buildFullRequestUrl(request));

      AccessDeniedException exception = new InvalidRequestTimeOrNonceException(timestamp);
      this.accessDeniedHandler.handle(request, response, exception);

      return;
    }

    // 判断该用户的 nonceStr 参数是否已经在 redis 中（防止短时间内的重放攻击）
    final Boolean haveNonce = redisTemplate.hasKey(nonce);
    if (StringUtils.isBlank(nonce) || Objects.isNull(haveNonce) || haveNonce) {
      this.logger.debug("invalid nonce " + UrlUtils.buildFullRequestUrl(request));

      AccessDeniedException exception = new InvalidRequestTimeOrNonceException(nonce);
      this.accessDeniedHandler.handle(request, response, exception);

      return;
    }

    ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
    String requestPayload = IOUtils.toString(requestWrapper.getContentAsByteArray(), StandardCharsets.UTF_8.displayName());

    // 对请求头参数进行签名
    if (StringUtils.isBlank(signature) || !StringUtils.equals(signature, SignatureUtil.signature(requestPayload, requestWrapper))) {
      this.logger.debug("invalid signature " + UrlUtils.buildFullRequestUrl(request));

      AccessDeniedException exception = new InvalidSignatureException(signature);
      this.accessDeniedHandler.handle(request, response, exception);

      return;
    }

    // 将本次用户请求的 nonceStr 参数存到 redis 中设置 xx 秒后自动删除
    redisTemplate.opsForValue().set(nonce, nonce, NONCE_STR_TIMEOUT_SECONDS, TimeUnit.SECONDS);

    filterChain.doFilter(requestWrapper, response);
  }
}
