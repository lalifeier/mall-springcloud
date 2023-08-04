package com.github.lalifeier.mall.cloud.auth.infrastructure.security.provider;

import com.github.lalifeier.mall.cloud.auth.infrastructure.security.token.PasswordAuthenticationToken;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
public class PasswordAuthenticationProvider implements AuthenticationProvider {

  private final PasswordEncoder passwordEncoder;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    return null;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return PasswordAuthenticationToken.class.isAssignableFrom(authentication);
  }
}
