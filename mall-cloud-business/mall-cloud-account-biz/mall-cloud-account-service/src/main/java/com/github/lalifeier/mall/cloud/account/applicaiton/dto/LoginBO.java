package com.github.lalifeier.mall.cloud.account.applicaiton.dto;

import lombok.Data;

@Data
public class LoginBO {
  private Long id;
  private String username;
  private String password;
  private String email;
  private String phone;
  private int code;
}
