package com.github.lalifeier.conversion.transform;

import com.github.lalifeier.conversion.entity.UserDo;
import com.github.lalifeier.conversion.entity.UserDto;

public class UserDtoToUserDoAssembler implements Assembler<UserDto, UserDo> {

    @Override
    public UserDo convert(UserDto userDto, Class<UserDo> target) {
        UserDo userDo = new UserDo();
        userDo.setId(userDto.getId());
        userDo.setName(userDto.getName());
        return userDo;
    }

}