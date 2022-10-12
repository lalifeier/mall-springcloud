package com.github.lalifeier.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {

    private Boolean onlyFetchByGateway = Boolean.TRUE;
}
