package com.github.lalifeier.mall.cloud.auth.infrastructure.persistence.jpa.converter;

import com.github.lalifeier.mall.cloud.auth.domain.user.model.entity.User;
import com.github.lalifeier.mall.cloud.auth.infrastructure.persistence.jpa.po.UserPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConverter {
  UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

  User toData(UserPO userPO);
}
