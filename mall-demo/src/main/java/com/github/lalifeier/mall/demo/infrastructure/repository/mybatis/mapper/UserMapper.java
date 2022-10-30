package com.github.lalifeier.mall.demo.infrastructure.repository.mybatis.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lalifeier.mall.demo.infrastructure.repository.mybatis.po.UserPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserPO> {

}