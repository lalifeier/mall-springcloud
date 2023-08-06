package com.github.lalifeier.mall.cloud.auth.infrastructure.security.config;

import com.github.lalifeier.mall.cloud.auth.domain.oauth2.service.UserService;
import com.github.lalifeier.mall.cloud.auth.infrastructure.security.filter.PasswordAuthenticationFilter;
import com.github.lalifeier.mall.cloud.auth.infrastructure.security.handler.RestAuthenticationFailureHandler;
import com.github.lalifeier.mall.cloud.auth.infrastructure.security.handler.RestAuthenticationSuccessHandler;
import com.github.lalifeier.mall.cloud.auth.infrastructure.security.provider.PasswordAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//@Component
public class PasswordAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
  private UserService userService;
  private PasswordEncoder passwordEncoder;
  private RestAuthenticationSuccessHandler authenticationSuccessHandler;
  private RestAuthenticationFailureHandler authenticationFailureHandler;

  @Override
  public void configure(HttpSecurity http) {
    PasswordAuthenticationFilter passwordAuthenticationFilter = new PasswordAuthenticationFilter();
    passwordAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
    passwordAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
    passwordAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);

    PasswordAuthenticationProvider passwordAuthenticationProvider = new PasswordAuthenticationProvider();
    passwordAuthenticationProvider.setUserService(userService);
    passwordAuthenticationProvider.setPasswordEncoder(passwordEncoder);

    http.authenticationProvider(passwordAuthenticationProvider)
      .addFilterAfter(passwordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
  }

  public UserService getUserService() {
    return userService;
  }

  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  public PasswordEncoder getPasswordEncoder() {
    return passwordEncoder;
  }

  public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  public RestAuthenticationSuccessHandler getAuthenticationSuccessHandler() {
    return authenticationSuccessHandler;
  }

  public void setAuthenticationSuccessHandler(RestAuthenticationSuccessHandler authenticationSuccessHandler) {
    this.authenticationSuccessHandler = authenticationSuccessHandler;
  }

  public RestAuthenticationFailureHandler getAuthenticationFailureHandler() {
    return authenticationFailureHandler;
  }

  public void setAuthenticationFailureHandler(RestAuthenticationFailureHandler authenticationFailureHandler) {
    this.authenticationFailureHandler = authenticationFailureHandler;
  }
}
