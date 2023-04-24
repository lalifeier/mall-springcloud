package com.github.lalifeier.mall.account.domain.account.service;

import com.github.lalifeier.mall.account.domain.account.model.valueobject.AccountName;
import com.github.lalifeier.mall.account.domain.account.model.valueobject.AccountPassword;

import javax.validation.constraints.NotNull;

public interface AccountDomainService {
  long register(@NotNull AccountName name, @NotNull AccountPassword password);

  boolean login(@NotNull AccountName name, @NotNull AccountPassword password);

  boolean changePassword(@NotNull AccountName name, @NotNull AccountPassword oldPassword, @NotNull AccountPassword newPassword);
}
