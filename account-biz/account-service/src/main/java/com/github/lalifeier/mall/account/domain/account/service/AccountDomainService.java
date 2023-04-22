package com.github.lalifeier.mall.account.domain.account.service;

public interface AccountDomainService {
  long register(String username, String password);

  boolean login(String username, String password);

  boolean changePassword(String username, String oldPassword, String newPassword);
}
