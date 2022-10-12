package com.github.lalifeier.config;

import com.github.lalifeier.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityAutoConfigure {

    @Bean
    public SecurityInterceptorConfigure securityInterceptorConfigure() {
        return new SecurityInterceptorConfigure();
    }

}
