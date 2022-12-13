package com.github.lalifeier.component;

import com.github.lalifeier.constant.SecurityConstant;
import com.github.lalifeier.utils.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import org.springframework.security.web.csrf.CsrfFilter;

@Slf4j
@Component
public class SignAuthFilter extends OncePerRequestFilter {

    private static final String SHOULD_NOT_FILTER = "SHOULD_NOT_FILTER" + CsrfFilter.class.getName();

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        //        Set<String> uriSet = new HashSet<>(securityProperties.getIgnoreSignUri());
//        String requestUri = request.getRequestURI();
//        boolean isMatch = false;
//        for (String uri : uriSet) {
//            isMatch = requestUri.contains(uri);
//            if (isMatch) {
//                break;
//            }
//        }
//        if (isMatch) {
//            return;
//        }
        return Boolean.TRUE.equals(request.getAttribute(SHOULD_NOT_FILTER));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取时间戳
        String timestamp = request.getHeader(SecurityConstant.SIGN_TIME);
        // 获取随机字符串
        String nonceStr = request.getHeader(SecurityConstant.SIGN_NONCE);
        // 获取签名
        String signature = request.getHeader(SecurityConstant.SIGN);

        // 判断时间是否大于 xx 秒（防止重放攻击）
        long NONCE_STR_TIMEOUT_SECONDS = 60L;
        if (StringUtils.isEmpty(timestamp) || Long.parseLong(timestamp) + (1000 * NONCE_STR_TIMEOUT_SECONDS) > System.currentTimeMillis()) {
//            invalid timestamp
        }

        // 判断该用户的 nonceStr 参数是否已经在 redis 中（防止短时间内的重放攻击）
//        final Boolean haveNonceStr = redisTemplate.hasKey(nonce);
//        if (StringUtils.isEmpty(nonce) || Objects.isNull(haveNonceStr) || haveNonceStr) {
//        invalid nonceStr
//        }

        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        String requestPayload = IOUtils.toString(requestWrapper.getContentAsByteArray(), StandardCharsets.UTF_8.displayName());

        // 对请求头参数进行签名
        if (StringUtils.isBlank(signature) || !Objects.equals(signature,  SignUtil.signature(timestamp, nonceStr, requestPayload, requestWrapper))) {
//            throw new CartisanException(CodeMessage.FAIL.fillArgs("invalid signature"));
        }

        // 将本次用户请求的 nonceStr 参数存到 redis 中设置 xx 秒后自动删除
//        redisTemplate.opsForValue().set(nonceStr, nonceStr, NONCE_STR_TIMEOUT_SECONDS, TimeUnit.SECONDS);

        filterChain.doFilter(requestWrapper, response);
    }

    public static void skipRequest(HttpServletRequest request) {
        request.setAttribute(SHOULD_NOT_FILTER, Boolean.TRUE);
    }
}
