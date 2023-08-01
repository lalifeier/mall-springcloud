package com.github.lalifeier.mall.cloud.account.api.authentication.model.dto;

import lombok.Data;

@Data
public class RegisterDTO {
  private Long id;
  private String username;
  private String email;
  private String phone;
}
