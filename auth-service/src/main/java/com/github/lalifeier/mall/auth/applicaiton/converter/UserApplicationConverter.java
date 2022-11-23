package com.github.lalifeier.mall.auth.applicaiton.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserApplicationConverter {
    UserApplicationConverter INSTANCE = Mappers.getMapper(UserApplicationConverter.class);

    //toDTO();

    //User toEntity(UserDTO userDTO);
}
