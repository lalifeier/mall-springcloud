package com.github.lalifeier.mall.cloud.auth.infrastructure.security.filter;

//import com.github.lalifeier.mall.cloud.auth.infrastructure.security.token.SmsAuthenticationToken;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.lang.Nullable;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationServiceException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//public class SmsAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
//
//  public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "phone";
//
//  public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "code";
//
//  private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/login",
//    "POST");
//
//  private String usernameParameter = SPRING_SECURITY_FORM_USERNAME_KEY;
//
//  private String passwordParameter = SPRING_SECURITY_FORM_PASSWORD_KEY;
//
//  private boolean postOnly = true;
//
//  public SmsAuthenticationFilter() {
//    super(DEFAULT_ANT_PATH_REQUEST_MATCHER);
//  }
//
//  public SmsAuthenticationFilter(AuthenticationManager authenticationManager) {
//    super(DEFAULT_ANT_PATH_REQUEST_MATCHER, authenticationManager);
//  }
//
//  @Override
//  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//    if (this.postOnly && !request.getMethod().equals("POST")) {
//      throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
//    }
//
//    String phone = obtainPhone(request);
//    phone = (phone != null) ? phone.trim() : "";
//    String code = obtainCode(request);
//    code = (code != null) ? code : "";
//
//    SmsAuthenticationToken authRequest = new SmsAuthenticationToken(phone, code);
//    setDetails(request, authRequest);
//    return this.getAuthenticationManager().authenticate(authRequest);
//  }
//
//  @Nullable
//  protected String obtainPhone(HttpServletRequest request) {
//    return request.getParameter(this.usernameParameter);
//  }
//
//  @Nullable
//  protected String obtainCode(HttpServletRequest request) {
//    return request.getParameter(this.passwordParameter);
//  }
//
//  protected void setDetails(HttpServletRequest request, SmsAuthenticationToken authRequest) {
//    authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
//  }
//}
