package com.github.lalifeier.mall.cloud.auth.infrastructure.security.token;

//import lombok.Getter;
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.SpringSecurityCoreVersion;
//
//import java.util.Collection;
//
//@Getter
//public class PasswordAuthenticationToken extends AbstractAuthenticationToken {
//  private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
//
//  private final String principal;
//
//  private final String password;
//
//  public PasswordAuthenticationToken(String principal, String password) {
//    super(null);
//    this.principal = principal;
//    this.password = password;
//    setAuthenticated(false);
//  }
//
//  public PasswordAuthenticationToken(String principal, String password, Collection<? extends GrantedAuthority> authorities) {
//    super(authorities);
//    this.principal = principal;
//    this.password = password;
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
//    return principal;
//  }
//}
