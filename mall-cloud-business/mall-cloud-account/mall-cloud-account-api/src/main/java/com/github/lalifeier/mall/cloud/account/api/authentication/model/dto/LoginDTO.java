package com.github.lalifeier.mall.cloud.account.api.authentication.model.dto;

import com.github.lalifeier.mall.cloud.common.enums.StatusEnum;

import lombok.Data;

@Data
public class LoginDTO {
  private Long id;
  private String username;
  private String email;
  private String phone;

  private StatusEnum status;
}
