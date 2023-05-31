package com.github.lalifeier.mall.logging.configure;

import com.github.lalifeier.mall.logging.aspect.SysLogAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SysLogAutoConfigure {

    @Bean
    public SysLogAspect controllerLogAspect() {
        return new SysLogAspect();
    }
}
