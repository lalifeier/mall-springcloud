package com.github.lalifeier.mall.cloud.account.model.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String type;
    private String username;
    private String password;
    private String email;
    private String phone;
    private int code;
}
