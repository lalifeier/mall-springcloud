package com.github.lalifeier.mall.account.domain.account.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.lalifeier.mall.account.domain.account.model.entity.Account;
import com.github.lalifeier.mall.account.domain.account.model.valueobject.Password;
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

    Account account = new Account();
    account.setUsername(username);
    account.setPassword(new Password(password));

    return accountRepository.save(account);
  }

  @Override
  public boolean login(String username, String password) {
    Account account = accountRepository.findByUsername(username);
    if (account == null) {
      return false;
    }

    return account.getPassword().verifyPassword(password);
  }

  @Override
  public boolean changePassword(String username, String oldPassword, String newPassword) {
    Account account = accountRepository.findByUsername(username);
    if (account == null) {
      throw new LoginException(LoginErrorCodes.USER_NOT_EXIST);
    }

    if (!account.getPassword().verifyPassword(oldPassword)) {
      throw new LoginException(LoginErrorCodes.PASSWORD_ERROR);
    }

    account.setPassword(new Password(newPassword));

    accountRepository.save(account);

    return true;
  }

}
