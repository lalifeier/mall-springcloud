package com.github.lalifeier.mall.common.conversion.transform;

import com.github.lalifeier.mall.common.conversion.entity.UserDo;
import com.github.lalifeier.mall.common.conversion.entity.UserPo;

public class UserPoToUserDoAssembler implements Assembler<UserPo, UserDo>{
    @Override
    public UserDo convert(UserPo userPo,Class<UserDo> target){
        UserDo userDo = new UserDo();
        userDo.setId(userPo.getId());
        userDo.setName(userPo.getName());
        return userDo;
    }
}