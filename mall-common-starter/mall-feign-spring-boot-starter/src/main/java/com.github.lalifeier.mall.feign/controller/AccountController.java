package com.github.lalifeier.mall.feign.controller;

import com.github.lalifeier.mall.feign.api.AccountFeign;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController implements AccountFeign {
    @Override
    public String test() {
        return null;
    }
}
