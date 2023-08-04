package com.github.lalifeier.mall.cloud.auth.infrastructure.security.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class OpenIdAuthenticationToken extends AbstractAuthenticationToken {
  public OpenIdAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
    super(authorities);
  }

  @Override
  public Object getCredentials() {
    return null;
  }

  @Override
  public Object getPrincipal() {
    return null;
  }
}
