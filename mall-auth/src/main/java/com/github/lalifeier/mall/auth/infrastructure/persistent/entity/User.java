package com.github.lalifeier.mall.auth.infrastructure.persistent.entity;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
}