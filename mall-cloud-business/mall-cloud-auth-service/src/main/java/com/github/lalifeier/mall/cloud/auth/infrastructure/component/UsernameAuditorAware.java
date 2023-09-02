package com.github.lalifeier.mall.cloud.auth.infrastructure.component;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class UsernameAuditorAware implements AuditorAware<String> {

  @Override
  public Optional<String> getCurrentAuditor() {
    return Optional.ofNullable(SecurityContextHolder.getContext())
        .map(SecurityContext::getAuthentication).filter(Authentication::isAuthenticated)
        .map(Authentication::getPrincipal).map(User.class::cast).map(User::getUsername);
  }
}
