package com.github.lalifeier.mall.cloud.auth.infrastructure.repository.persistence.mysql.po;

import com.github.lalifeier.mall.cloud.jpa.po.BasePO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class UserPO extends BasePO {
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "enabled", nullable = false, columnDefinition = "TINYINT", length = 1)
    private boolean enabled;
}
