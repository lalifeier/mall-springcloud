package com.github.lalifeier.mall.cloud.auth.infrastructure.security.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

public class RestLogoutHandler implements LogoutHandler {
  @Override
  public void logout(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) {}
}
