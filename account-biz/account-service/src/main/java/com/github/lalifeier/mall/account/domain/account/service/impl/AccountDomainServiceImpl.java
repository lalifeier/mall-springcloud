package com.github.lalifeier.mall.account.domain.account.service.impl;

import com.github.lalifeier.mall.account.domain.account.model.entity.Account;
import com.github.lalifeier.mall.account.domain.account.model.valueobject.AccountName;
import com.github.lalifeier.mall.account.domain.account.model.valueobject.AccountPassword;
import com.github.lalifeier.mall.account.domain.account.repository.AccountRepository;
import com.github.lalifeier.mall.account.domain.account.service.AccountDomainService;
import com.github.lalifeier.mall.account.infrastructure.error.LoginErrorCodes;
import com.github.lalifeier.mall.account.infrastructure.error.LoginException;
import com.github.lalifeier.mall.account.infrastructure.error.RegisterErrorCodes;
import com.github.lalifeier.mall.account.infrastructure.error.RegisterException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@Service
public class AccountDomainServiceImpl implements AccountDomainService {
  @Resource
  private AccountRepository accountRepository;

  @Override
  public long register(@NotNull AccountName name, @NotNull AccountPassword password) {
    if (accountRepository.findByUsername(name.getName()) != null) {
      throw new RegisterException(RegisterErrorCodes.USER_EXIST);
    }

    Account account = new Account();
    account.setUsername(name);
    account.setPassword(password);

    accountRepository.save(account);

    return 0;
  }

  @Override
  public boolean login(@NotNull AccountName name, @NotNull AccountPassword password) {
    Account account = accountRepository.findByUsername(name.getName());
    if (account == null) {
      return false;
    }

    return account.getPassword().verifyPassword(password.getEncryptPassword());
  }

  @Override
  public boolean changePassword(@NotNull AccountName username, @NotNull AccountPassword oldPassword, @NotNull AccountPassword newPassword) {
    Account account = accountRepository.findByUsername(username.getName());
    if (account == null) {
      throw new LoginException(LoginErrorCodes.USER_NOT_EXIST);
    }

    if (!account.getPassword().verifyPassword(oldPassword.getEncryptPassword())) {
      throw new LoginException(LoginErrorCodes.PASSWORD_ERROR);
    }

    account.setPassword(newPassword);

    accountRepository.save(account);

    return true;
  }

}
