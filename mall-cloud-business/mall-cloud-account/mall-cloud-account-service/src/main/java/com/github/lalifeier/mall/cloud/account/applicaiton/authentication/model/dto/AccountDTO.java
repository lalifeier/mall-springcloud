package com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.dto;

import lombok.Data;

@Data
public class AccountDTO {
  private Long id;
  private String username;
  private String password;
  private String email;
  private String phone;
}
