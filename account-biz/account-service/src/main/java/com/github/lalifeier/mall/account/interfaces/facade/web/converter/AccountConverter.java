package com.github.lalifeier.mall.account.interfaces.facade.web.converter;

import com.github.lalifeier.mall.account.applicaiton.dto.AccountBO;
import com.github.lalifeier.mall.account.interfaces.facade.web.model.request.LoginRequest;
import com.github.lalifeier.mall.account.interfaces.facade.web.model.request.RegisterRequest;
import com.github.lalifeier.mall.account.interfaces.facade.web.model.response.AccountVO;
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
