package com.github.lalifeier.mall.cloud.mybatisplus.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseTenantEntity extends BaseEntity {

  /** 租户 id */
  private Long tenantId;
}
