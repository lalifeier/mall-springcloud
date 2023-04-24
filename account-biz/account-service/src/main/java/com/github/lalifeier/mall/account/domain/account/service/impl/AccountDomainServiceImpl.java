package com.github.lalifeier.mall.account.domain.account.service.impl;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.github.lalifeier.mall.account.domain.account.model.entity.Account;
import com.github.lalifeier.mall.account.domain.account.model.valueobject.AccountName;
import com.github.lalifeier.mall.account.domain.account.model.valueobject.AccountPassword;
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



}
