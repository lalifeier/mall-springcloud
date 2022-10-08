package com.github.lalifeier.mall.logging.config;

import com.github.lalifeier.mall.logging.aspect.SysLogAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SysLogAutoConfig {

    @Bean
    public SysLogAspect controllerLogAspect() {
        return new SysLogAspect();
    }
}
