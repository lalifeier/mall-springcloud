package com.github.lalifeier.mall.cloud.account.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
  public AccountDO save(@NotNull AccountDO aggregate) {
    AccountUserPO accountUserPO = accountConverter.convert(aggregate);

    if (aggregate.getId() != null && aggregate.getId().getValue() > 0) {
      accountUserMapper.updateById(accountUserPO);
    } else {
      accountUserMapper.insert(accountUserPO);
      aggregate.setId(new AccountId(accountUserPO.getId()));
    }
    return aggregate;
  }

  @Override
  public AccountDO findByUsername(AccountName name) {
    LambdaQueryWrapper<AccountUserPO> queryWrapper = Wrappers.lambdaQuery();
    queryWrapper.eq(AccountUserPO::getUsername, name.getValue());
    AccountUserPO accountUserPO = accountUserMapper.selectOne(queryWrapper);
    return accountConverter.convert(accountUserPO);
  }

  @Override
  public AccountDO findByEmail(Email email) {
    LambdaQueryWrapper<AccountUserPO> queryWrapper = Wrappers.lambdaQuery();
    queryWrapper.eq(AccountUserPO::getEmail, email.getValue());
    AccountUserPO accountUserPO = accountUserMapper.selectOne(queryWrapper);
    return accountConverter.convert(accountUserPO);
  }

  @Override
  public AccountDO findByPhone(PhoneNumber phone) {
    LambdaQueryWrapper<AccountUserPO> queryWrapper = Wrappers.lambdaQuery();
    queryWrapper.eq(AccountUserPO::getPhone, phone.getValue());
    AccountUserPO accountUserPO = accountUserMapper.selectOne(queryWrapper);
    return accountConverter.convert(accountUserPO);
  }

  @Override
  public Long countByUserNameOrEmailOrPhone(AccountName name, Email email, PhoneNumber phone) {
    LambdaQueryWrapper<AccountUserPO> queryWrapper = Wrappers.lambdaQuery();
    queryWrapper.
      or().eq(AccountUserPO::getUsername, name.getValue()).
      or().eq(AccountUserPO::getEmail, email.getValue()).
      or().eq(AccountUserPO::getPhone, phone.getValue());
    return accountUserMapper.selectCount(queryWrapper);
  }
}
