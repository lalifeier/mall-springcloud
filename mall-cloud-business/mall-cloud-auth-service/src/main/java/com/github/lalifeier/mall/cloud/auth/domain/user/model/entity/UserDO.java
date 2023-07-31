package com.github.lalifeier.mall.cloud.auth.domain.user.model.entity;

import com.github.lalifeier.mall.cloud.auth.domain.user.model.valueobject.Role;
import com.github.lalifeier.mall.cloud.auth.domain.user.model.valueobject.UserId;
import java.util.List;
import lombok.Data;

@Data
public class UserDO {

    private UserId id;

    private String username;

    private String password;

    private String email;

    private Boolean enabled;

    private List<Role> roles;
}
