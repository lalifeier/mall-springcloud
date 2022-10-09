package com.github.lalifeier.config;

import com.github.lalifeier.aspect.SysLogAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SysLogAutoConfig {

    @Bean
    public SysLogAspect controllerLogAspect() {
        return new SysLogAspect();
    }
}
