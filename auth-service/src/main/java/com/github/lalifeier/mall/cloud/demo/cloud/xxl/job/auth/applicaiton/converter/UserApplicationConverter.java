package com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.auth.applicaiton.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserApplicationConverter {
    UserApplicationConverter INSTANCE = Mappers.getMapper(UserApplicationConverter.class);

    //toDTO();

    //User toEntity(UserDTO userDTO);
}
