package com.github.lalifeier.mall.auth.infrastructure.repository.persistence.mysql.po;

import com.github.lalifeier.model.po.BasePO;
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
