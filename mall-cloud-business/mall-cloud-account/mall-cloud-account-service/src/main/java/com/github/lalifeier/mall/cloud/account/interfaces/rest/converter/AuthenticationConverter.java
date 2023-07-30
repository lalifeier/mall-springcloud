package com.github.lalifeier.mall.cloud.account.interfaces.rest.converter;

import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.dto.LoginDTO;
import com.github.lalifeier.mall.cloud.account.model.response.LoginResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "META-INF.spring")
public interface AuthenticationConverter {
  AuthenticationConverter INSTANCE = Mappers.getMapper(AuthenticationConverter.class);

  LoginResponse toVO(LoginDTO loginDTO);
}
