package com.github.lalifeier.mall.cloud.account.infrastructure.persistence.mybatis.converter;

import com.github.lalifeier.mall.cloud.account.domain.account.model.entity.Account;
import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.AccountPassword;
import com.github.lalifeier.mall.cloud.account.infrastructure.persistence.mybatis.po.AccountUserPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountConverter {
    AccountConverter INSTANCE = Mappers.getMapper(AccountConverter.class);

    @Mapping(source = "id", target = "id.value")
    @Mapping(source = "username", target = "username.value")
    @Mapping(target = "password", qualifiedByName = "encryptPassword")
    @Mapping(source = "email", target = "email.value")
    @Mapping(source = "phone", target = "phone.value")
    Account toData(AccountUserPO accountUserPO);

    @Named("encryptPassword")
    default AccountPassword encryptPassword(String password) {
        return new AccountPassword(new AccountPassword.EncryptPassword(password));
    }

    @Mapping(source = "id.value", target = "id")
    @Mapping(source = "username.value", target = "username")
    @Mapping(source = "password.encryptPassword", target = "password")
    @Mapping(source = "email.value", target = "email")
    @Mapping(source = "phone.value", target = "phone")
    @Mapping(target = "createdTime", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedTime", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    AccountUserPO fromData(Account account);
}
