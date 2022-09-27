package com.github.lalifeier.mall.auth.service;


import cn.hutool.crypto.digest.MD5;
import com.alibaba.nacos.shaded.com.google.gson.Gson;
import com.github.lalifeier.mall.auth.infrastructure.persistent.entity.User;
import com.github.lalifeier.mall.auth.infrastructure.persistent.jpa.UserJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class UserTest {

    @Autowired
    private UserJpaRepository userJpaRepository;


    @Test
    public void createUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword(MD5.create().digestHex("123456"));
        log.info("save user: [{}]", new Gson().toJson(userJpaRepository.save(user)));
    }
}
