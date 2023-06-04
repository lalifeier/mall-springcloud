package com.github.lalifeier.mall.cloud.account.infrastructure.repository;

import com.github.lalifeier.mall.cloud.account.domain.account.model.entity.AccountDO;
import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.AccountId;
import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.AccountName;
import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.Email;
import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.PhoneNumber;
import com.github.lalifeier.mall.cloud.account.domain.account.repository.AccountRepository;
import com.github.lalifeier.mall.cloud.account.infrastructure.repository.mybatis.converter.AccountConverter;
import com.github.lalifeier.mall.cloud.account.infrastructure.repository.mybatis.mapper.AccountUserMapper;
import com.github.lalifeier.mall.cloud.account.infrastructure.repository.mybatis.po.AccountUserPO;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

  @Resource
  private AccountUserMapper accountUserMapper;

  private final AccountConverter accountConverter;

  public AccountRepositoryImpl() {
    this.accountConverter = AccountConverter.INSTANCE;
  }

  @Override
  public void attach(@NotNull AccountDO aggregate) {

  }

  @Override
  public void detach(@NotNull AccountDO aggregate) {

  }

  @Override
  public AccountDO find(@NotNull AccountId accountId) {
    Long id = accountId.getValue();
    AccountUserPO accountUserPO = accountUserMapper.selectById(id);
    return accountConverter.convert(accountUserPO);
  }

  @Override
  public void remove(@NotNull AccountDO aggregate) {
    AccountUserPO accountUserPO = accountConverter.convert(aggregate);
    accountUserMapper.deleteById(accountUserPO);
  }

  @Override
  public void save(@NotNull AccountDO aggregate) {
    AccountUserPO accountUserPO = accountConverter.convert(aggregate);

    if (aggregate.getId() != null && aggregate.getId().getValue() > 0) {
      accountUserMapper.updateById(accountUserPO);
    } else {
      accountUserMapper.insert(accountUserPO);
      aggregate.setId(new AccountId(accountUserPO.getId()));
    }
  }

  @Override
  public AccountDO findByUsername(AccountName name) {
    AccountUserPO accountUserPO = accountUserMapper.findByUsername(name.getValue());
    return accountConverter.convert(accountUserPO);
  }

  @Override
  public AccountDO findByEmail(Email email) {
    AccountUserPO accountUserPO = accountUserMapper.findByUsername(email.getValue());
    return accountConverter.convert(accountUserPO);
  }

  @Override
  public AccountDO findByPhone(PhoneNumber phone) {
    AccountUserPO accountUserPO = accountUserMapper.findByUsername(phone.getValue());
    return accountConverter.convert(accountUserPO);
  }
}
