package com.github.lalifeier.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "springdoc.api-docs.enabled", havingValue = "true")
public class SpringDocAutoConfig {
    @Bean
    @ConfigurationProperties(prefix = "springdoc.api-docs.info")
    public Info springDocInfo() {
        return new Info();
    }

    @Bean
    public OpenAPI openAPI(Info info) {
        return new OpenAPI().info(info);
    }
}
