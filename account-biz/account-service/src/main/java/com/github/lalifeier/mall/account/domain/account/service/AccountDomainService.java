package com.github.lalifeier.mall.account.domain.account.service;

import com.github.lalifeier.mall.account.domain.account.model.entity.AccountDO;

public interface AccountDomainService {
  AccountDO register(String username, String password);

  boolean login(String username, String password);

  boolean changePassword(String username, String oldPassword, String newPassword);
}
