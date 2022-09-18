package com.github.lalifeier.mall.admin.domain.aggregate.user.entity;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
