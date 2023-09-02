package com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject;

import com.github.lalifeier.mall.cloud.common.model.marker.ValueObject;
import jakarta.validation.ValidationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AccountPassword implements ValueObject<AccountPassword> {
  private final EncryptPassword encryptPassword;

  public AccountPassword(String password) {
    if (StringUtils.isBlank(password)) {
      throw new ValidationException("密码不能为空");
    }

    this.encryptPassword = new EncryptPassword(generateEncryptPassword(password));
  }

  public AccountPassword(EncryptPassword encryptPassword) {
    this.encryptPassword = encryptPassword;
  }

  public boolean verifyPassword(String password) {
    return new BCryptPasswordEncoder().matches(password, getEncryptPassword());
  }

  private String generateEncryptPassword(String password) {
    return new BCryptPasswordEncoder().encode(password);
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
  public boolean sameValueAs(AccountPassword other) {
    return other != null && this.encryptPassword.equals(other.encryptPassword);
  }
}
