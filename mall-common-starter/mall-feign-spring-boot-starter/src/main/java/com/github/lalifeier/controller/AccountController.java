package com.github.lalifeier.controller;

import com.github.lalifeier.api.AccountFeign;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController implements AccountFeign {
    @Override
    public String test() {
        return null;
    }
}
