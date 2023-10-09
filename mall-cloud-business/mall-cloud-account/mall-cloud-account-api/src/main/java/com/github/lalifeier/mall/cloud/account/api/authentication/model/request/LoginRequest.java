package com.github.lalifeier.mall.cloud.account.api.authentication.model.request;

import com.github.lalifeier.mall.cloud.account.api.authentication.enums.LoginTypeEnum;
import lombok.Data;

@Data
public class LoginRequest {
    // @NotBlank(message = "登录类型不能为空")
    // @JsonDeserialize(using = BaseEnumDeserializer.class)
    private LoginTypeEnum type;

    private String username;
    private String email;
    private String phone;
    private String code;
    private String password;
}
