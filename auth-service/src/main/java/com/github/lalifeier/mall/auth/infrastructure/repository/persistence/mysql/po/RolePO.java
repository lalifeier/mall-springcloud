package com.github.lalifeier.mall.auth.infrastructure.repository.persistence.mysql.po;


import com.github.lalifeier.mall.jpa.po.BasePO;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
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
