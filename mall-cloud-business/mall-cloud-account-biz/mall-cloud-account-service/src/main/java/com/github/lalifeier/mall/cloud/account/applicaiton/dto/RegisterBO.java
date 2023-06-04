package com.github.lalifeier.mall.cloud.account.applicaiton.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterBO {
  private String username;
  private String password;
  private String email;
  private String phone;
  private int code;
  private String type;
}
