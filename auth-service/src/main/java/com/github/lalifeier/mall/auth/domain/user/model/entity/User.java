package com.github.lalifeier.mall.auth.domain.user.model.entity;

import com.github.lalifeier.mall.auth.domain.user.model.valueobject.UserId;
import com.github.lalifeier.common.model.AggregateRoot;
import lombok.Data;

@Data
public class User implements AggregateRoot {

    private UserId id;

    private String username;

    private String password;

    private String email;

    private boolean enabled;

    //private List<Role> roles;
}
