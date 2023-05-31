package com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.account.interfaces.facade.web.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountConverter {
  AccountConverter INSTANCE = Mappers.getMapper(AccountConverter.class);

  //AccountBO toDTO(RegisterRequest registerRequest);
  //
  //AccountBO toDTO(LoginRequest loginRequest);
  //
  //AccountVO toVO(AccountBO accountBO);
}
