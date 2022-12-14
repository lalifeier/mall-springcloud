package com.github.lalifeier.mall.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "spring.security")
public class SecurityProperties {

    private List<String> ignoreSignUri;

    private Integer signTimeout;

    private Boolean onlyFetchByGateway = Boolean.TRUE;
}
