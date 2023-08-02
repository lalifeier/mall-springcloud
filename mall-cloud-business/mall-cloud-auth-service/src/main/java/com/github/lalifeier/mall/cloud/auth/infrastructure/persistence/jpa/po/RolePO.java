package com.github.lalifeier.mall.cloud.auth.infrastructure.persistence.jpa.po;

import com.github.lalifeier.mall.cloud.jpa.po.BasePO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "role")
public class RolePO extends BasePO {

  @Column(name = "name", unique = true, nullable = false)
  private String name;

  @Column(name = "sort", nullable = false, columnDefinition = "TINYINT", length = 1)
  private boolean sort;

  @Column(name = "status", nullable = false, columnDefinition = "TINYINT", length = 1)
  private boolean status;

  @Column(name = "remark")
  private String remark;
}
