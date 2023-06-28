package com.github.lalifeier.mall.cloud.common.utils.context;

import lombok.Data;

@Data
public class UserContext {

  private Long userId;

  private String userName;

  private String clientIP;
}
