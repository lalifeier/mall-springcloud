package com.github.lalifeier.mall.account.domain.account.model.valueobject;

import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
public class AccountPassword {
  private final BCryptPasswordEncoder encryptPassword;

  public AccountPassword(BCryptPasswordEncoder encryptPassword) {
    this.encryptPassword = encryptPassword;
  }

//  private String getSalt() {
//    return getEncryptPassword().substring(getEncryptPassword().length() - 31);
//  }

  public AccountPassword(String password) {
    String salt = BCrypt.gensalt();
    this.encryptPassword = new BCryptPasswordEncoder().encode(password + salt);
  }



}
