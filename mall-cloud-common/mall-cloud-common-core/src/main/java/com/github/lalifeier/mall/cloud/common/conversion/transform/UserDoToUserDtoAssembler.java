package com.github.lalifeier.mall.cloud.common.conversion.transform;

import com.github.lalifeier.mall.cloud.common.conversion.entity.UserDo;
import com.github.lalifeier.mall.cloud.common.conversion.entity.UserDto;

public class UserDoToUserDtoAssembler implements Assembler<UserDo, UserDto> {
    @Override
    public UserDto convert(UserDo userDo, Class<UserDto> target) {
        UserDto userDto = new UserDto();
        userDto.setId(userDo.getId());
        userDto.setName(userDo.getName());
        return userDto;
    }
}
