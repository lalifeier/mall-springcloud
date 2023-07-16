package com.github.lalifeier.mall.cloud.common.context;


import lombok.Data;

@Data
public class RequestContext {
  /**
   * 客户端ip
   */
  private String clientIp;

  /**
   * 请求路径
   */
  private String requestUri;


  private String traceId;
}
