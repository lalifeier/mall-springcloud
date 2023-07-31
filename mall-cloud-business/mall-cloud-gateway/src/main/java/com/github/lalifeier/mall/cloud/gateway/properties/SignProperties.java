package com.github.lalifeier.mall.cloud.gateway.properties;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@EqualsAndHashCode(callSuper = false)
@Component
@ConfigurationProperties(prefix = "gateway.sign")
public class SignProperties {

    /** 是否启用 */
    private Boolean enabled;

    /** 签名超时时间 */
    private Integer timeout;

    /** 允许未签名访问的 url 地址 */
    private List<String> whiteUrls;
}
