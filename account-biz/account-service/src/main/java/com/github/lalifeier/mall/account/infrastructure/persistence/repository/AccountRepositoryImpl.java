package com.github.lalifeier.mall.account.infrastructure.persistence.repository;

import com.github.lalifeier.mall.account.domain.account.model.entity.Account;
import com.github.lalifeier.mall.account.domain.account.model.valueobject.AccountId;
import com.github.lalifeier.mall.account.domain.account.repository.AccountRepository;
import com.github.lalifeier.mall.account.infrastructure.converter.AccountConverter;
import com.github.lalifeier.mall.account.infrastructure.persistence.mybatis.mapper.AccountUserMapper;
import com.github.lalifeier.mall.account.infrastructure.persistence.mybatis.po.AccountUserPO;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

  @Resource
  private AccountUserMapper accountUserMapper;

  private final AccountConverter converter;

  public AccountRepositoryImpl() {
    this.converter = AccountConverter.INSTANCE;
  }

  @Override
  public void attach(@NotNull Account aggregate) {

  }

  @Override
  public void detach(@NotNull Account aggregate) {

  }

  @Override
  public Account find(@NotNull AccountId accountId) {
    AccountUserPO accountUserPO = accountUserMapper.selectById(accountId.getValue());
    return converter.fromData(accountUserPO);
  }

  @Override
  public void remove(@NotNull Account aggregate) {
    AccountUserPO accountUserPO = converter.toData(aggregate);
    accountUserMapper.deleteById(accountUserPO);
  }

  @Override
  public void save(@NotNull Account aggregate) {
    AccountUserPO accountUserPO = converter.toData(aggregate);

    if (aggregate.getId() != null && aggregate.getId().getValue() > 0) {
      accountUserMapper.updateById(accountUserPO);
      return;
    }

    accountUserMapper.insert(accountUserPO);
    aggregate.setId(converter.fromData(accountUserPO).getId());
  }

  @Override
  public Account findByUsername(String username) {
    AccountUserPO accountUserPO = accountUserMapper.findByUsername(username);
    return converter.fromData(accountUserPO);
  }
}
