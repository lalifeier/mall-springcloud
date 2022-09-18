package com.github.lalifeier.mall.auth.infrastructure.persistent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.github.lalifeier.mall.auth.infrastructure.persistent.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}