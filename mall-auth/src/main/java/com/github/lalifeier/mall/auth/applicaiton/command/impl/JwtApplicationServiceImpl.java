//package com.github.lalifeier.mall.auth.applicaiton.command.impl;
//
//import com.github.lalifeier.mall.auth.applicaiton.command.JwtApplicationService;
//import com.github.lalifeier.mall.auth.infrastructure.persistent.jpa.UserJpaRepository;
//import com.github.lalifeier.mall.common.domain.User;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//
//@Slf4j
//@Service
//@Transactional(rollbackFor = Exception.class)
//public class JwtApplicationServiceImpl implements JwtApplicationService {
//
//    @Autowired
//    private UserJpaRepository userJpaRepository;
//
//    @Override
//    public String generateToken(String username, String password) throws Exception {
//        return null;
//    }
//
//    @Override
//    public String generateToken(String username, String password, int expire) throws Exception {
//        return null;
//    }
//
//    @Override
//    public String registerUserAndGenerateToken(User user) throws Exception {
//        return null;
//    }
//}
