package com.github.lalifeier.mall.cloud.doc.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(
        prefix = "springdoc.api-docs",
        name = "enabled",
        havingValue = "true",
        matchIfMissing = true)
public class DocAutoConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "springdoc.api-docs.info")
    public Info springDocInfo() {
        return new Info();
    }

    @Bean
    public OpenAPI openAPI(Info apiInfo) {
        return new OpenAPI().info(apiInfo);
    }
}
