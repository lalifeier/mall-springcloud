package com.github.lalifeier.mall.cloud.auth.infrastructure.persistence.jpa.converter;

import com.github.lalifeier.mall.cloud.auth.domain.oauth2.entity.UserPrincipal;
import com.github.lalifeier.mall.cloud.auth.infrastructure.persistence.jpa.po.UserPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConverter {
  UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

  @Mapping(target = "authorities", ignore = true)
  UserPrincipal toData(UserPO userPO);
}
