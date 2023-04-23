package com.github.lalifeier.mall.account.infrastructure.persistence.repository;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.github.lalifeier.mall.account.domain.account.model.entity.Account;
import com.github.lalifeier.mall.account.domain.account.repository.AccountRepository;
import com.github.lalifeier.mall.account.infrastructure.mapper.AccountMapper;
import com.github.lalifeier.mall.account.infrastructure.persistence.mybatis.mapper.AccountUserMapper;
import com.github.lalifeier.mall.account.infrastructure.persistence.mybatis.po.AccountUserPO;

@Repository
public class AccountRepositoryImpl implements AccountRepository {
  @Resource
  private AccountUserMapper accountUserMapper;

  @Override
  public Long save(Account accountDO) {
    AccountUserPO accountUserPO = AccountMapper.INSTANCE.toAccountUserPO(accountDO);
    accountUserMapper.insert(accountUserPO);
    return accountUserPO.getId();
  }

  @Override
  public Account findByUsername(String username) {
    AccountUserPO accountUserPO = accountUserMapper.findByUsername(username);
    return AccountMapper.INSTANCE.toAccountDO(accountUserPO);
  }
}
