package com.github.lalifeier.mall.account.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.github.lalifeier.mall.account.domain.account.model.entity.AccountDO;
import com.github.lalifeier.mall.account.infrastructure.persistence.mybatis.po.AccountUserPO;

@Mapper
public interface AccountMapper {
  AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

  AccountDO toAccountDO(AccountUserPO accountUserPO);

  AccountUserPO toAccountUserPO(AccountDO accountDO);
}
