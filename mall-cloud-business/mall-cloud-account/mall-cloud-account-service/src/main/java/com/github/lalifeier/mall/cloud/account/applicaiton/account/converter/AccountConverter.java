package com.github.lalifeier.mall.cloud.account.applicaiton.account.converter;

import com.github.lalifeier.mall.cloud.account.applicaiton.account.dto.AccountDTO;
import com.github.lalifeier.mall.cloud.account.applicaiton.account.dto.RegisterDTO;
import com.github.lalifeier.mall.cloud.account.domain.account.model.entity.AccountDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountConverter {

  AccountConverter INSTANCE = Mappers.getMapper(AccountConverter.class);

  @Mapping(target = "id", ignore = true)
  @Mapping(source = "username", target = "username.value")
  @Mapping(source = "password", target = "password.value")
  @Mapping(source = "email", target = "email.value")
  @Mapping(source = "phone", target = "phone.value")
  AccountDO convert(RegisterDTO registerBO);


  AccountDTO convert(AccountDO accountDO);
}
