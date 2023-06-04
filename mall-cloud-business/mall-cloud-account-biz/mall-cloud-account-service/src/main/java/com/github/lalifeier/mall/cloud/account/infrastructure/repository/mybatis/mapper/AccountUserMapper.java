package com.github.lalifeier.mall.cloud.account.infrastructure.repository.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lalifeier.mall.cloud.account.infrastructure.repository.mybatis.po.AccountUserPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountUserMapper extends BaseMapper<AccountUserPO> {
  AccountUserPO findByUsername(String username);

  AccountUserPO findByEmail(String email);

  AccountUserPO findByPhone(String phone);
}
