package com.github.lalifeier.mall.account.interfaces.web.model.request;

//import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class RegisterRequest {
  private String id;

  //@NotBlank(message = "用户名必填")
  private String username;

  //@NotBlank(message = "密码必填")
  private String password;
}
