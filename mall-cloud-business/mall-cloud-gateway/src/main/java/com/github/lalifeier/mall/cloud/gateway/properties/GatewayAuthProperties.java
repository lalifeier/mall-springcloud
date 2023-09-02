package com.github.lalifeier.mall.cloud.gateway.properties;

import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "gateway")
public class GatewayAuthProperties {

  /** 白名单 url 地址 */
  private List<String> whiteUrls;

  /** 黑名单 url 地址 */
  private List<String> blankUrls;

  /** 签名 */
  private SignProperties sign = new SignProperties();
}
