package com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.auth.applicaiton.service.command.impl;


import com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.auth.domain.user.service.UserDomainService;
import com.github.lalifeier.mall.cloud.demo.cloud.xxl.job.auth.applicaiton.service.command.UserCommandApplicationService;
import org.springframework.stereotype.Service;

@Service
public class UserCommandApplicationServiceImpl implements UserCommandApplicationService {

    //@Autowired
    //private UserApplicationConverter userApplicationConverter;
    private final UserDomainService userDomainService;


    public UserCommandApplicationServiceImpl(UserDomainService userDomainService) {
        this.userDomainService = userDomainService;
    }
}
