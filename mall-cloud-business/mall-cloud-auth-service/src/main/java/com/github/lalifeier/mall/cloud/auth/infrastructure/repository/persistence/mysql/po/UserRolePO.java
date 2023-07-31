package com.github.lalifeier.mall.cloud.auth.infrastructure.repository.persistence.mysql.po;

import com.github.lalifeier.mall.cloud.jpa.po.BasePO;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

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
