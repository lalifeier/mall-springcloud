package com.github.lalifeier.mall.cloud.auth.infrastructure.persistence.jpa.po;

import com.github.lalifeier.mall.cloud.jpa.po.BasePO;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "user_role")
public class UserRolePO extends BasePO {

  /**
   * 用户id
   */
  private Integer userId;

  /**
   * 角色id
   */
  private Integer roleId;
}
