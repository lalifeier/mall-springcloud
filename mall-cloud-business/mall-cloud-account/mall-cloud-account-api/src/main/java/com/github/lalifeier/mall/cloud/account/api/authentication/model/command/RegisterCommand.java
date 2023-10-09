package com.github.lalifeier.mall.cloud.account.api.authentication.model.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterCommand {
    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @Email(message = "请输入正确的邮箱格式")
    private String email;

    @NotBlank(message = "手机号不能为空")
    private String phone;
}
