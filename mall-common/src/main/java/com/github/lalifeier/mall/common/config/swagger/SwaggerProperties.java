package com.github.lalifeier.mall.common.config.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "swagger")
@Data
public final class SwaggerProperties {
    private Boolean enable;
    private String groupName;
    private String basePackage;

    private String version;
    private String title;
    private String description;


    private String termsOfServiceUrl;

    private String contactName;
    private String contactEmail;
    private String contactUrl;
    
    private String license;
    private String licenseUrl;
}
