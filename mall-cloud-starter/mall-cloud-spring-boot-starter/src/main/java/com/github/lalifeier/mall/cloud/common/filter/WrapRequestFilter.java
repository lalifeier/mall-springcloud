package com.github.lalifeier.mall.cloud.common.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@WebFilter
public class WrapRequestFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        filterChain.doFilter(requestWrapper, responseWrapper);

        responseWrapper.copyBodyToResponse();
    }
}

// @WebFilter(urlPatterns = "/*")
// public class WrapRequestFilter implements Filter {
//
//  private static final String FORM_CONTENT_TYPE = "multipart/form-data";
//
//  @Override
//  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//    throws IOException, ServletException {
//    String contentType = request.getContentType();
//
//    if (response instanceof HttpServletResponse) {
//      ContentCachingResponseWrapper responseWrapper = new
// ContentCachingResponseWrapper((HttpServletResponse) response);
//    }
//
//
//    if (request instanceof HttpServletRequest) {
//      HttpServletRequest requestWrapper = new ContentCachingRequestWrapper((HttpServletRequest)
// request);
//      if (contentType != null && contentType.contains(FORM_CONTENT_TYPE)) {
//        chain.doFilter(request, response);
//      } else {
//        chain.doFilter(requestWrapper, response);
//      }
//      return;
//    }
//    chain.doFilter(request, response);
//  }
// }
