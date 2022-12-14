package com.github.lalifeier.common.conversion.transform;

import com.github.lalifeier.common.conversion.entity.UserDo;
import com.github.lalifeier.common.conversion.entity.UserPo;

public class UserDoToUserPoAssembler implements Assembler<UserDo, UserPo> {
    @Override
    public UserPo convert(UserDo userDo, Class<UserPo> target) {
        UserPo userPo = new UserPo();
        userPo.setId(userDo.getId());
        userPo.setName(userDo.getName());
        return userPo;
    }
}
