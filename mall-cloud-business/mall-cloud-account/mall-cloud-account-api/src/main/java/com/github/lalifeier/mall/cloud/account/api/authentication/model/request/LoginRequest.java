package com.github.lalifeier.mall.cloud.account.api.authentication.model.request;

import com.github.lalifeier.mall.cloud.account.api.authentication.enums.LoginTypeEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Valid
public class LoginRequest {
    @NotBlank(message = "登录类型不能为空")
    private LoginTypeEnum type;

    private String username;
    private String email;
    private String phone;
    private String code;
    private String password;
}
