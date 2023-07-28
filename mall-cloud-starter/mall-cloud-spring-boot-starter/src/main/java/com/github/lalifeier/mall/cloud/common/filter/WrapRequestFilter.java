package com.github.lalifeier.mall.cloud.common.filter;

import com.github.lalifeier.mall.cloud.common.wrapper.BodyReaderHttpServletRequestWrapper;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@WebFilter
public class WrapRequestFilter implements Filter {
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    ServletRequest requestWrapper = null;
    if (request instanceof HttpServletRequest) {
      requestWrapper = new BodyReaderHttpServletRequestWrapper((HttpServletRequest) request);
    }
    if (null == requestWrapper) {
      chain.doFilter(request, response);
    } else {
      chain.doFilter(requestWrapper, response);
    }
  }
}
