package com.github.lalifeier.mall.account.infrastructure.persistence.repository;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.github.lalifeier.mall.account.domain.account.repository.AccountRepository;
import com.github.lalifeier.mall.account.infrastructure.persistence.jpa.repository.AccountJpaRepository;

@Repository
public class AccountRepositoryImpl implements AccountRepository {
  @Resource
  private AccountJpaRepository accountJpaRepository;
}
