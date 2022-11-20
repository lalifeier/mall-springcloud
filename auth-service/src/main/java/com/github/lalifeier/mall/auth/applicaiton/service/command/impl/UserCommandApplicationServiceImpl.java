package com.github.lalifeier.mall.auth.applicaiton.service.command.impl;


import com.github.lalifeier.mall.auth.applicaiton.converter.UserApplicationConverter;
import com.github.lalifeier.mall.auth.applicaiton.service.command.UserCommandApplicationService;
import com.github.lalifeier.mall.auth.domain.user.service.UserDomainService;
import org.springframework.stereotype.Service;

@Service
public class UserCommandApplicationServiceImpl implements UserCommandApplicationService {

    private final UserApplicationConverter userApplicationConverter;
    private final UserDomainService userDomainService;


    public UserCommandApplicationServiceImpl(UserApplicationConverter userApplicationConverter, UserDomainService userDomainService) {
        this.userApplicationConverter = userApplicationConverter;
        this.userDomainService = userDomainService;
    }
}
