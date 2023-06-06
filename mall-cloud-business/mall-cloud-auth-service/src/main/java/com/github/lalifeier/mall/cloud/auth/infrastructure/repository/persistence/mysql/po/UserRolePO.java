package com.github.lalifeier.mall.cloud.auth.infrastructure.repository.persistence.mysql.po;


import com.github.lalifeier.mall.cloud.jpa.po.BasePO;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_role")
public class UserRolePO extends BasePO {

    private Long userId;

    private Long roleId;
}