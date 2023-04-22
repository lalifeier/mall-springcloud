package com.github.lalifeier.mall.account.domain.account.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.lalifeier.mall.account.domain.account.model.entity.AccountDO;
import com.github.lalifeier.mall.account.domain.account.repository.AccountRepository;
import com.github.lalifeier.mall.account.domain.account.service.AccountDomainService;
import com.github.lalifeier.mall.account.infrastructure.error.LoginErrorCodes;
import com.github.lalifeier.mall.account.infrastructure.error.LoginException;
import com.github.lalifeier.mall.account.infrastructure.error.RegisterErrorCodes;
import com.github.lalifeier.mall.account.infrastructure.error.RegisterException;

@Service
public class AccountDomainServiceImpl implements AccountDomainService {
  @Resource
  private AccountRepository accountRepository;

  @Override
  public long register(String username, String password) {
    if (accountRepository.findByUsername(username) != null) {
      throw new RegisterException(RegisterErrorCodes.USER_EXIST);
    }

    AccountDO account = new AccountDO(username, password);

    return accountRepository.save(account);
  }

  @Override
  public boolean login(String username, String password) {
    AccountDO account = accountRepository.findByUsername(username);
    if (account == null) {
      return false;
    }

    return account.verifyPassword(password);
  }

  @Override
  public boolean changePassword(String username, String oldPassword, String newPassword) {
    AccountDO account = accountRepository.findByUsername(username);
    if (account == null) {
      throw new LoginException(LoginErrorCodes.USER_NOT_EXIST);
    }

    account.changePassword(oldPassword, newPassword);
    accountRepository.save(account);

    return true;
  }

}
