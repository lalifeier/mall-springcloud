package com.github.lalifeier.mall.gateway.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Component
@ConfigurationProperties(prefix = "gateway")
public class GatewayAuthProperties {

    /**
     * 白名单 url 地址
     */
    private List<String> whiteUrls;


    /**
     * 黑名单 url 地址
     */
    private List<String> blankUrls;

    /**
     * 签名
     */
    private SignProperties sign = new SignProperties();
}