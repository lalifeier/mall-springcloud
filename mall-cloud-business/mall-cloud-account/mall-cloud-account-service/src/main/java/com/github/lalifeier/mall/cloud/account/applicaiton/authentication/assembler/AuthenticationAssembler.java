package com.github.lalifeier.mall.cloud.account.applicaiton.authentication.assembler;

import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.dto.LoginDTO;
import com.github.lalifeier.mall.cloud.account.domain.account.model.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthenticationAssembler {
  AuthenticationAssembler INSTANCE = Mappers.getMapper(AuthenticationAssembler.class);

//  @Mapping(target = "id", ignore = true)
//  @Mapping(source = "username", target = "username.value")
//  @Mapping(source = "password", target = "password.value")
//  @Mapping(source = "email", target = "email.value")
//  @Mapping(source = "phone", target = "phone.value")
//  Account convert(RegisterDTO registerBO);

  @Mapping(source = "id.value", target = "userId")
  @Mapping(source = "username.value", target = "username")
  LoginDTO convert(Account account);


//  toEntity()
}
