package com.github.lalifeier.mall.cloud.auth.infrastructure.security.config;

import com.github.lalifeier.mall.cloud.auth.domain.oauth2.service.UserService;
import com.github.lalifeier.mall.cloud.auth.infrastructure.handler.RestAuthenticationFailureHandler;
import com.github.lalifeier.mall.cloud.auth.infrastructure.handler.RestAuthenticationSuccessHandler;
import com.github.lalifeier.mall.cloud.auth.infrastructure.security.filter.SmsAuthenticationFilter;
import com.github.lalifeier.mall.cloud.auth.infrastructure.security.provider.SmsAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class SmsAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

  @Autowired
  private UserService userService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private RestAuthenticationSuccessHandler authenticationSuccessHandler;

  @Autowired
  private RestAuthenticationFailureHandler authenticationFailureHandler;

  @Override
  public void configure(HttpSecurity http) {
    SmsAuthenticationFilter smsAuthenticationFilter = new SmsAuthenticationFilter();
    smsAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
    smsAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
    smsAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);

    SmsAuthenticationProvider smsAuthenticationProvider = new SmsAuthenticationProvider();
    smsAuthenticationProvider.setUserService(userService);

    http.authenticationProvider(smsAuthenticationProvider)
      .addFilterAfter(smsAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
  }
}
