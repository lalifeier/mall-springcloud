package com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.auth.interfaces.converter;

import com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.auth.applicaiton.model.command.CreateUserCommand;
import com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.auth.interfaces.model.req.CreateUserReq;
import com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.auth.interfaces.model.req.UpdateUserReq;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConverter {
    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    //Object toDTO(RegisterReq registerReq);
    //
    //Object toDTO(LoginReq loginReq);

    CreateUserCommand toDTO(CreateUserReq createUserReq);

    UpdateUserReq toDTO(UpdateUserReq updateUserReq);
}
