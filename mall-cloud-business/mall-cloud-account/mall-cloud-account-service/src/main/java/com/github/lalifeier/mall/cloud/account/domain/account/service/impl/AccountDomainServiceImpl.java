package com.github.lalifeier.mall.cloud.account.domain.account.service.impl;

import com.github.lalifeier.mall.cloud.account.domain.account.model.entity.Account;
import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.AccountId;
import com.github.lalifeier.mall.cloud.account.domain.account.repository.AccountRepository;
import com.github.lalifeier.mall.cloud.account.domain.account.service.AccountDomainService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountDomainServiceImpl implements AccountDomainService {
  @Resource
  private AccountRepository accountRepository;

  @Override
  public void create(Account account) {
    accountRepository.save(account);
  }

  @Override
  public void update(Account account) {
    accountRepository.save(account);
  }

  @Override
  public Account get(AccountId accountId) {
    return accountRepository.find(accountId);
  }

  @Override
  public void delete(AccountId accountId) {
    Account account = accountRepository.find(accountId);
    if (account != null) {
      accountRepository.remove(account);
    }
  }
}
