package com.github.lalifeier.config;

import com.github.lalifeier.interceptor.ServerProtectInterceptor;
import com.github.lalifeier.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


public class SecurityInterceptorConfigure implements WebMvcConfigurer {

    private SecurityProperties properties;

    @Autowired
    public void setProperties(SecurityProperties properties) {
        this.properties = properties;
    }

    public HandlerInterceptor serverProtectInterceptor() {
        ServerProtectInterceptor interceptor = new ServerProtectInterceptor();
        interceptor.setProperties(properties);
        return interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(serverProtectInterceptor());
    }
}
