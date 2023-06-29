package com.github.lalifeier.mall.cloud.account.infrastructure.persistence.mybatis.converter;

import com.github.lalifeier.mall.cloud.account.domain.account.model.entity.Account;
import com.github.lalifeier.mall.cloud.account.infrastructure.persistence.mybatis.po.AccountUserPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AccountConverter {
  AccountConverter INSTANCE = Mappers.getMapper(AccountConverter.class);

  @Mapping(source = "id", target = "id.value")
  Account convert(AccountUserPO accountUserPO);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "createdBy", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "updatedBy", ignore = true)
  @Mapping(target = "isDeleted", ignore = true)
  AccountUserPO convert(Account account);

  List<Account> convertList(List<AccountUserPO> accountUserPOList);
}
