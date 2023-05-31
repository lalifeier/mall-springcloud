package com.github.lalifeier.mall.cloud.account.domain.account.service.impl;

import javax.annotation.Resource;

import com.github.lalifeier.mall.cloud.account.domain.account.repository.AccountRepository;
import org.springframework.stereotype.Service;

import com.github.lalifeier.mall.cloud.account.domain.account.service.AccountDomainService;

@Service
public class AccountDomainServiceImpl implements AccountDomainService {
  @Resource
  private AccountRepository accountRepository;



}
