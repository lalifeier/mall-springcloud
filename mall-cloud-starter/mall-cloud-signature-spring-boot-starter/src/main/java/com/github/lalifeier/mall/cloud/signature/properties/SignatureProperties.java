package com.github.lalifeier.mall.cloud.signature.properties;

import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.signature")
public class SignatureProperties {

    /** 是否启用 */
    private Boolean enabled;

    /** 签名超时时间 */
    private Integer timeout;

    /** API签名白名单 */
    private List<String> whitelist;

    /** 重放攻击时间，默认60秒 */
    private Long replayAttackTime = 60L;
}
