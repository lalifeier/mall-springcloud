package com.github.lalifeier.mall.auth.infrastructure.repository.persistence.mysql.converter;

import com.github.lalifeier.mall.auth.domain.user.model.entity.UserDO;
import com.github.lalifeier.mall.auth.infrastructure.repository.persistence.mysql.po.UserPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConverter {
    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    UserDO toDO(UserPO userPO);
}
