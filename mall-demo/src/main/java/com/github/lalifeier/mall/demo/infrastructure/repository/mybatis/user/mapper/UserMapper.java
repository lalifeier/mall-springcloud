package com.github.lalifeier.mall.demo.infrastructure.repository.mybatis.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lalifeier.mall.demo.infrastructure.repository.mybatis.user.po.UserPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserPO> {

}
