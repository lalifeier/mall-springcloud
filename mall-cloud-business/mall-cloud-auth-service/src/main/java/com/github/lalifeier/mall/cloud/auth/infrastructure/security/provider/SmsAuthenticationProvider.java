package com.github.lalifeier.mall.cloud.auth.infrastructure.security.provider;

// import com.github.lalifeier.mall.cloud.auth.domain.oauth2.service.UserService;
// import com.github.lalifeier.mall.cloud.auth.infrastructure.security.token.SmsAuthenticationToken;
// import org.springframework.security.authentication.AuthenticationProvider;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.AuthenticationException;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
// public class SmsAuthenticationProvider implements AuthenticationProvider {
//  private UserService userService;
//
//  @Override
//  public Authentication authenticate(Authentication authentication) throws AuthenticationException
// {
//    SmsAuthenticationToken smsAuthenticationToken = (SmsAuthenticationToken) authentication;
//    String phone = smsAuthenticationToken.getPhone();
//    String code = smsAuthenticationToken.getCode();
//
//    UserDetails userDetails = userService.loadUserByUsername(phone);
//
//    if (userDetails == null) {
//      throw new UsernameNotFoundException("手机号不存在");
//    }
//
//    SmsAuthenticationToken authenticationToken = new SmsAuthenticationToken(phone, null,
// userDetails.getAuthorities());
//    authenticationToken.setDetails(smsAuthenticationToken.getDetails());
//
//    return authenticationToken;
//  }
//
//  @Override
//  public boolean supports(Class<?> authentication) {
//    return SmsAuthenticationToken.class.isAssignableFrom(authentication);
//  }
//
//  public UserService getUserService() {
//    return userService;
//  }
//
//  public void setUserService(UserService userService) {
//    this.userService = userService;
//  }
// }
