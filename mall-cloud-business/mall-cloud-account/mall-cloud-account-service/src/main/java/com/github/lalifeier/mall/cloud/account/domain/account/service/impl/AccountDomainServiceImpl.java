package com.github.lalifeier.mall.cloud.account.domain.account.service.impl;

import com.github.lalifeier.mall.cloud.account.domain.account.repository.AccountRepository;
import com.github.lalifeier.mall.cloud.account.domain.account.service.AccountDomainService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountDomainServiceImpl implements AccountDomainService {
  @Resource
  private AccountRepository accountRepository;
}
