package com.github.lalifeier.mall.account.vo;


import lombok.Data;

//import javax.validation.constraints.NotBlank;

@Data
public class ValidVO {
    private String id;

    //@NotBlank(message = "用户名必填")
    private String username;

    //@NotBlank(message = "密码必填")
    private String password;
}
