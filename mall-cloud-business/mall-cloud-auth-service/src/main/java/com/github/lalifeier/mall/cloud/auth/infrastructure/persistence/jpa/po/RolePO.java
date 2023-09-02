package com.github.lalifeier.mall.cloud.auth.infrastructure.persistence.jpa.po;

import com.github.lalifeier.mall.cloud.jpa.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "role")
public class RolePO extends BaseEntity<Long> {

  /** 角色名称 */
  private String name;

  /** 排序 */
  private Integer sort;

  /** 状态 0:禁用 1:启用 */
  private Integer status;

  /** 备注 */
  private String remark;
}
