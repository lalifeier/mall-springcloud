package com.github.lalifeier.mall.cloud.auth.infrastructure.persistence.jpa.po;

import com.github.lalifeier.mall.cloud.jpa.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user_role")
public class UserRolePO extends BaseEntity<Long> {

  /** 用户id */
  private Integer userId;

  /** 角色id */
  private Integer roleId;
}
