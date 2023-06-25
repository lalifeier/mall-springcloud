package com.github.lalifeier.mall.cloud.account.applicaiton.authentication.dto.login;

import com.github.lalifeier.mall.cloud.account.infrastructure.enums.LoginType;
import lombok.Data;

@Data
public abstract class LoginDTO {
  public abstract LoginType getLoginType();
}
