package com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.account.interfaces.facade.web.model.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class RegisterRequest {
  @NotBlank(message = "用户名必填")
  private String username;

  @NotBlank(message = "密码必填")
  private String password;
}
