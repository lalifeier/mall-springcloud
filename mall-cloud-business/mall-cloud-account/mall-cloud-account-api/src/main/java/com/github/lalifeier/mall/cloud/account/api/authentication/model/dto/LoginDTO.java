package com.github.lalifeier.mall.cloud.account.api.authentication.model.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private Long id;
    private String username;
    private String email;
    private String phone;
}
