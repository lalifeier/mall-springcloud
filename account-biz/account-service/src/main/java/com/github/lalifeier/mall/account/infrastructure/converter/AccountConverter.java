package com.github.lalifeier.mall.account.infrastructure.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.github.lalifeier.mall.account.domain.account.model.entity.Account;
import com.github.lalifeier.mall.account.infrastructure.persistence.mybatis.po.AccountUserPO;

@Mapper
public interface AccountConverter {
  AccountConverter INSTANCE = Mappers.getMapper(AccountConverter.class);

  Account fromData(AccountUserPO accountUserPO);

  AccountUserPO toData(Account aggregate);
}
