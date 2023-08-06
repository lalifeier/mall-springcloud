package com.github.lalifeier.mall.cloud.auth.infrastructure.handler;

import com.github.lalifeier.mall.cloud.common.enums.HttpErrorCodeEnum;
import com.github.lalifeier.mall.cloud.common.model.result.Result;
import com.github.lalifeier.mall.cloud.common.utils.JSONUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class RestAuthenticationFailureHandler implements AuthenticationFailureHandler {
  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
    Result result = Result.failure(HttpErrorCodeEnum.UNAUTHORIZED, exception.getMessage());

    response.setCharacterEncoding(StandardCharsets.UTF_8.name());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.getWriter().write(JSONUtil.toJson(result));
    response.getWriter().flush();
  }
}
