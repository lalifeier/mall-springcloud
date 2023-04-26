package com.github.lalifeier.mall.account.domain.account.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.lalifeier.mall.account.domain.account.repository.AccountRepository;
import com.github.lalifeier.mall.account.domain.account.service.AccountDomainService;

@Service
public class AccountDomainServiceImpl implements AccountDomainService {
  @Resource
  private AccountRepository accountRepository;



}
