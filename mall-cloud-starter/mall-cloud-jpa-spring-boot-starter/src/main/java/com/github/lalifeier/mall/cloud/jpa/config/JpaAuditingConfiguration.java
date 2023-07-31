package com.github.lalifeier.mall.cloud.jpa.config;

import com.github.lalifeier.mall.cloud.jpa.audit.AuditorAwareImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public AuditorAware<String> auditorProvider() {

        /*
         if you are using spring security, you can get the currently logged username with following code segment.
         SecurityContextHolder.getContext().getAuthentication().getName()
        */
        return new AuditorAwareImpl();
    }
}
