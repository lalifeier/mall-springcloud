package com.github.lalifeier.mall.cloud.auth.infrastructure.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.github.lalifeier.mall"})
@EntityScan(basePackages = {"com.github.lalifeier.mall"})
// @EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JPAConfig {

    //    @Bean
    //    AuditorAware<String> auditorProvider() {
    //        return new UsernameAuditorAware();
    //    }
}
