package com.github.lalifeier.mall.cloud.common.context.user;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UserContextFilter implements Filter {

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain
    filterChain) throws IOException, ServletException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

//    UserContextHolder.getContext().setUserId();

    filterChain.doFilter(httpServletRequest, servletResponse);
  }
}
