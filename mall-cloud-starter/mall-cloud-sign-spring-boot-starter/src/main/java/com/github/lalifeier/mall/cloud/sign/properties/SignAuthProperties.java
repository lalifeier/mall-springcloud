package com.github.lalifeier.mall.cloud.sign.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "spring.sign")
public class SignAuthProperties {

    /**
     * 是否启用
     */
    private Boolean enabled;

    /**
     * 签名超时时间
     */
    private Integer timeout;

    /**
     * 允许未签名访问的 url 地址
     */
    private List<String> ignoreUri;
}
