package com.github.lalifeier.mall.account.domain.account.model.valueobject;

import javax.validation.ValidationException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.github.lalifeier.mall.common.model.ValueObject;

public class Password implements ValueObject<Password> {
  private final EncryptPassword encryptPassword;

  public Password(String password) {
    if (StringUtils.isBlank(password)) {
      throw new ValidationException("密码不能为空");
    }

    this.encryptPassword = new EncryptPassword(generateEncryptPassword(password));
  }

  public Password(EncryptPassword encryptPassword) {
    this.encryptPassword = encryptPassword;
  }

  private String getSalt() {
    return getEncryptPassword().substring(getEncryptPassword().length() - 31);
  }

  public boolean verifyPassword(String password) {
    return new BCryptPasswordEncoder().matches(password + getSalt(), getEncryptPassword());
  }

  private String generateEncryptPassword(String password) {
    String salt = BCrypt.gensalt();
    return new BCryptPasswordEncoder().encode(password + salt);
  }

  public String getEncryptPassword() {
    return encryptPassword.getEncryptPassword();
  }

  public static class EncryptPassword {

    private final String encryptPassword;

    public EncryptPassword(String encryptPassword) {
      this.encryptPassword = encryptPassword;
    }

    public String getEncryptPassword() {
      return encryptPassword;
    }
  }

  @Override
  public boolean sameValueAs(Password other) {
    return other != null && this.encryptPassword.equals(other.encryptPassword);
  }

}
