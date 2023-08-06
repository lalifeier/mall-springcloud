package com.github.lalifeier.mall.cloud.auth.infrastructure.security.token;

//import lombok.Getter;
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.SpringSecurityCoreVersion;
//
//import java.util.Collection;
//
//@Getter
//public class SmsAuthenticationToken extends AbstractAuthenticationToken {
//
//  private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
//
//  private final String phone;
//
//  private final String code;
//
//  public SmsAuthenticationToken(String phone, String code) {
//    super(null);
//    this.phone = phone;
//    this.code = code;
//    setAuthenticated(false);
//  }
//
//  public SmsAuthenticationToken(String phone, String code, Collection<? extends GrantedAuthority> authorities) {
//    super(authorities);
//    this.phone = phone;
//    this.code = code;
//    super.setAuthenticated(true);
//  }
//
//  @Override
//  public Object getCredentials() {
//    throw new UnsupportedOperationException();
//  }
//
//  @Override
//  public Object getPrincipal() {
//    throw new UnsupportedOperationException();
//  }
//}
