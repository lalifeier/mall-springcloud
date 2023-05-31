package com.github.lalifeier.mall.cloud.account.infrastructure.persistence.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lalifeier.mall.cloud.account.infrastructure.persistence.mybatis.po.AccountUserPO;

@Mapper
public interface AccountUserMapper extends BaseMapper<AccountUserPO> {
  AccountUserPO findByUsername(String username);
}
