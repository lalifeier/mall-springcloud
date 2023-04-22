package com.github.lalifeier.mall.account.domain.account.service.impl;

import com.github.lalifeier.mall.account.domain.account.model.entity.AccountDO;
import com.github.lalifeier.mall.account.domain.account.repository.AccountRepository;
import com.github.lalifeier.mall.account.domain.account.service.AccountDomainService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountDomainServiceImpl implements AccountDomainService {
  @Resource
  private AccountRepository accountRepository;

  @Resource
  private PasswordEncoder passwordEncoder;

  @Override
  public AccountDO register(String username, String password) {
    if (accountRepository.findByUsername(username) != null) {
      throw new IllegalArgumentException("用户名已存在");
    }

    String encryptedPassword = passwordEncoder.encode(password);
    AccountDO account = new AccountDO(username, encryptedPassword);
    accountRepository.save(account);

    return account;
  }

  @Override
  public boolean login(String username, String password) {
    AccountDO account = accountRepository.findByUsername(username);
    if (account == null) {
      return false;
    }

    return account.verifyPassword(passwordEncoder.encode(password));
  }

  @Override
  public boolean changePassword(String username, String oldPassword, String newPassword) {
    AccountDO account = accountRepository.findByUsername(username);
    if (account == null) {
      throw new IllegalStateException("用户不存在");
    }

    String encryptedNewPassword = passwordEncoder.encode(newPassword);
    account.changePassword(oldPassword, encryptedNewPassword);
    accountRepository.save(account);

    return true;
  }

}
