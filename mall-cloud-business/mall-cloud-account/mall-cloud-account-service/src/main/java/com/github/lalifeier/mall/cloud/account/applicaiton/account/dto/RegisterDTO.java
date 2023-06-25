package com.github.lalifeier.mall.cloud.account.applicaiton.account.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterDTO {
  private String username;
  private String password;
  private String email;
  private String phone;
  private int code;
  private String type;
}
