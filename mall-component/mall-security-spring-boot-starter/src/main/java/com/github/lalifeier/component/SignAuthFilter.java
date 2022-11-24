//package com.github.lalifeier.component;
//
//import com.github.lalifeier.properties.SecurityProperties;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.xml.ws.RequestWrapper;
//import java.io.IOException;
//import java.util.HashSet;
//import java.util.Set;
//
//@Slf4j
//@Component
//public class SignAuthFilter implements Filter {
//    @Autowired
//    private SecurityProperties securityProperties;
//
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
//        HttpServletRequest requestWrapper = new RequestWrapper(httpRequest);
//
//        Set<String> uriSet = new HashSet<>(securityProperties.getIgnoreSignUri());
//        String requestUri = httpRequest.getRequestURI();
//        boolean isMatch = false;
//        for (String uri : uriSet) {
//            isMatch = requestUri.contains(uri);
//            if (isMatch) {
//                break;
//            }
//        }
//        log.info("当前请求的URI是==>{},isMatch==>{}", httpRequest.getRequestURI(), isMatch);
//        if (isMatch) {
//            filterChain.doFilter(requestWrapper, servletResponse);
//            return;
//        }
//
//        String sign = requestWrapper.getHeader("Sign");
//    }
//
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
//}
