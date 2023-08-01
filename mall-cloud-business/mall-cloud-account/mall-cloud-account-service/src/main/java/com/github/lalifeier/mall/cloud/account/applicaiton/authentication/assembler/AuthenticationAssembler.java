package com.github.lalifeier.mall.cloud.account.applicaiton.authentication.assembler;


import com.github.lalifeier.mall.cloud.account.api.authentication.model.command.RegisterCommand;
import com.github.lalifeier.mall.cloud.account.api.authentication.model.dto.LoginDTO;
import com.github.lalifeier.mall.cloud.account.api.authentication.model.dto.RegisterDTO;
import com.github.lalifeier.mall.cloud.account.domain.account.model.entity.Account;
import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.AccountPassword;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthenticationAssembler {
  AuthenticationAssembler INSTANCE = Mappers.getMapper(AuthenticationAssembler.class);

  @Mapping(source = "username", target = "username.value")
  @Mapping(target = "password", qualifiedByName = "encryptPassword")
  @Mapping(source = "email", target = "email.value")
  @Mapping(source = "phone", target = "phone.value")
  Account toEntity(RegisterCommand command);

  @Named("encryptPassword")
  default AccountPassword encryptPassword(String password) {
    return new AccountPassword(password);
  }

  @Mapping(source = "id.value", target = "id")
  @Mapping(source = "username.value", target = "username")
  @Mapping(source = "email.value", target = "email")
  @Mapping(source = "phone.value", target = "phone")
  LoginDTO toLoginDTO(Account account);

  @Mapping(source = "id.value", target = "id")
  @Mapping(source = "username.value", target = "username")
  @Mapping(source = "email.value", target = "email")
  @Mapping(source = "phone.value", target = "phone")
  RegisterDTO toRegisterDTO(Account account);
}
