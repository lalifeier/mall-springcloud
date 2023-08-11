package com.github.lalifeier.mall.cloud.auth.infrastructure.persistence.jpa.po;

import com.github.lalifeier.mall.cloud.jpa.po.BasePO;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "role")
public class RolePO extends BasePO {

    /** 角色名称 */
    private String name;

    /** 排序 */
    private Integer sort;

    /** 状态 0:禁用 1:启用 */
    private Integer status;

    /** 备注 */
    private String remark;
}
