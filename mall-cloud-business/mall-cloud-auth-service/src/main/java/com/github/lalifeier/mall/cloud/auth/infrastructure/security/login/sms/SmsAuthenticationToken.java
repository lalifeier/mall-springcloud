package com.github.lalifeier.mall.cloud.auth.infrastructure.security.login.sms;

import java.util.Collection;
import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

@Getter
public class SmsAuthenticationToken extends AbstractAuthenticationToken {

  private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

  private final String phone;

  private final String code;

  public SmsAuthenticationToken(String phone, String code) {
    super(null);
    this.phone = phone;
    this.code = code;
    setAuthenticated(false);
  }

  public SmsAuthenticationToken(String phone, String code,
      Collection<? extends GrantedAuthority> authorities) {
    super(authorities);
    this.phone = phone;
    this.code = code;
    super.setAuthenticated(true);
  }

  public static SmsAuthenticationToken unauthenticated(String phone, String code) {
    return new SmsAuthenticationToken(phone, code);
  }

  public static SmsAuthenticationToken authenticated(String phone, String code,
      Collection<? extends GrantedAuthority> authorities) {
    return new SmsAuthenticationToken(phone, code, authorities);
  }

  @Override
  public Object getCredentials() {
    return phone;
  }

  @Override
  public Object getPrincipal() {
    return code;
  }
}
