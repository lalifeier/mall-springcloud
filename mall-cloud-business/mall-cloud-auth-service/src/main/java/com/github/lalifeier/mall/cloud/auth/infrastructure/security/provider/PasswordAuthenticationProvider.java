package com.github.lalifeier.mall.cloud.auth.infrastructure.security.provider;

import com.github.lalifeier.mall.cloud.auth.domain.oauth2.service.UserService;
import com.github.lalifeier.mall.cloud.auth.infrastructure.security.token.PasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;


public class PasswordAuthenticationProvider implements AuthenticationProvider {
  private PasswordEncoder passwordEncoder;
  private UserService userService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    PasswordAuthenticationToken passwordAuthenticationToken = (PasswordAuthenticationToken) authentication;
    String principal = (String) passwordAuthenticationToken.getPrincipal();
    String password = passwordAuthenticationToken.getPassword();

    UserDetails userDetails = userService.loadUserByUsername(principal);

    if (userDetails == null) {
      throw new UsernameNotFoundException("用户不存在");
    }

    PasswordAuthenticationToken authenticationToken = new PasswordAuthenticationToken(principal, null, userDetails.getAuthorities());
    authenticationToken.setDetails(authenticationToken.getDetails());

    return authenticationToken;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return PasswordAuthenticationToken.class.isAssignableFrom(authentication);
  }

  public PasswordEncoder getPasswordEncoder() {
    return passwordEncoder;
  }

  public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  public UserService getUserService() {
    return userService;
  }

  public void setUserService(UserService userService) {
    this.userService = userService;
  }
}
