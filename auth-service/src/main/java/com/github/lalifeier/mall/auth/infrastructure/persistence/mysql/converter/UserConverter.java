package com.github.lalifeier.mall.auth.infrastructure.persistence.mysql.converter;

import com.github.lalifeier.mall.auth.domain.user.model.entity.User;
import com.github.lalifeier.mall.auth.infrastructure.persistence.mysql.po.UserPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConverter {
    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    User toDo(UserPO userPO);
}
