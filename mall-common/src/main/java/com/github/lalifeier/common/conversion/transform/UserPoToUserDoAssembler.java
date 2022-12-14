package com.github.lalifeier.common.conversion.transform;

import com.github.lalifeier.common.conversion.entity.UserDo;
import com.github.lalifeier.common.conversion.entity.UserPo;

public class UserPoToUserDoAssembler implements Assembler<UserPo, UserDo> {
    @Override
    public UserDo convert(UserPo userPo, Class<UserDo> target) {
        UserDo userDo = new UserDo();
        userDo.setId(userPo.getId());
        userDo.setName(userPo.getName());
        return userDo;
    }
}
