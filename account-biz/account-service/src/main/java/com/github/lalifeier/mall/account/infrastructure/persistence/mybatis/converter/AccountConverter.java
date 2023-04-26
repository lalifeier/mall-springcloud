package com.github.lalifeier.mall.account.infrastructure.persistence.mybatis.converter;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.github.lalifeier.mall.account.domain.account.model.entity.AccountDO;
import com.github.lalifeier.mall.account.infrastructure.persistence.mybatis.po.AccountUserPO;

import java.util.List;

@Mapper
public interface AccountConverter {
  AccountConverter INSTANCE = Mappers.getMapper(AccountConverter.class);

  AccountDO toAccountDO(AccountUserPO accountUserPO);

  AccountUserPO toAccountUserPO(AccountDO accountDO);

  List<AccountDO> toAccountDOList(List<AccountUserPO> accountUserPOList);
}
