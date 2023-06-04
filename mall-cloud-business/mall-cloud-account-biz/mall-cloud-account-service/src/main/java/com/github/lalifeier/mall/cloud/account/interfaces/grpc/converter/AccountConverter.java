package com.github.lalifeier.mall.cloud.account.interfaces.grpc.converter;

import com.github.lalifeier.mall.cloud.account.applicaiton.dto.AccountBO;
import com.github.lalifeier.mall.cloud.account.applicaiton.dto.RegisterBO;
import com.github.lalifeier.mall.cloud.account.interfaces.grpc.RegisterRequest;
import com.github.lalifeier.mall.cloud.account.interfaces.grpc.RegisterResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountConverter {

  AccountConverter INSTANCE = Mappers.getMapper(AccountConverter.class);

  //@Mapping(target = "id", ignore = true)
  //@Mapping(source = "username", target = "username.value")
  //@Mapping(source = "password", target = "password.value")
  //@Mapping(source = "email", target = "email.value")
  //@Mapping(source = "phone", target = "phone.value")
  //AccountDO convert(RegisterBO registerBO);


  RegisterBO convert(RegisterRequest request);

  RegisterResponse convert(AccountBO accountBO);
}
