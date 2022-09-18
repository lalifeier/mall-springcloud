package com.github.lalifeier.mall.auth.applicaiton.query.impl;


import com.github.lalifeier.mall.auth.applicaiton.query.UserQueryApplicationService;
import com.github.lalifeier.mall.auth.infrastructure.persistent.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.lalifeier.mall.auth.infrastructure.persistent.entity.User;
import java.util.List;

@Service
public class UserQueryApplicationServiceImpl implements UserQueryApplicationService {

    @Autowired
    private UserMapper userMapper;

    public List<User> findAll(){
        return userMapper.selectList(null);
    }
}
