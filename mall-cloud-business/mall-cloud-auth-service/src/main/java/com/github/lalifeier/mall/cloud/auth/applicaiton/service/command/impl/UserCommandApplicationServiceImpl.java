package com.github.lalifeier.mall.cloud.auth.applicaiton.service.command.impl;

import com.github.lalifeier.mall.cloud.auth.applicaiton.service.command.UserCommandApplicationService;
import com.github.lalifeier.mall.cloud.auth.domain.user.service.UserDomainService;
import org.springframework.stereotype.Service;

@Service
public class UserCommandApplicationServiceImpl implements UserCommandApplicationService {

    // @Autowired
    // private UserApplicationConverter userApplicationConverter;
    private final UserDomainService userDomainService;

    public UserCommandApplicationServiceImpl(UserDomainService userDomainService) {
        this.userDomainService = userDomainService;
    }
}
