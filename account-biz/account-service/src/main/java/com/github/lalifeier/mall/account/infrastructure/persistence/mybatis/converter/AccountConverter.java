package com.github.lalifeier.mall.account.infrastructure.persistence.mybatis.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.github.lalifeier.mall.account.domain.account.model.entity.AccountDO;
import com.github.lalifeier.mall.account.infrastructure.persistence.mybatis.po.AccountUserPO;

import java.util.List;

@Mapper
public interface AccountConverter {
  AccountConverter INSTANCE = Mappers.getMapper(AccountConverter.class);

  @Mapping(source = "id", target = "id.value")
  AccountDO toAccountDO(AccountUserPO accountUserPO);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "createdBy", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "updatedBy", ignore = true)
  @Mapping(target = "isDeleted", ignore = true)
  AccountUserPO toAccountUserPO(AccountDO accountDO);

  List<AccountDO> toAccountDOList(List<AccountUserPO> accountUserPOList);
}
