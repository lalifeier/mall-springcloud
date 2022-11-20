package com.github.lalifeier.mall.auth.applicaiton.converter;

import com.github.lalifeier.mall.auth.applicaiton.model.dto.UserDTO;
import com.github.lalifeier.mall.auth.domain.user.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserApplicationConverter {
    UserApplicationConverter INSTANCE = Mappers.getMapper(UserApplicationConverter.class);

    //toDTO();

    User toEntity(UserDTO userDTO);
}
