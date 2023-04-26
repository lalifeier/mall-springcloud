package com.github.lalifeier.mall.account.interfaces.facade.web;

import javax.annotation.Resource;

import com.github.lalifeier.mall.account.interfaces.facade.web.model.request.LoginRequest;
import com.github.lalifeier.mall.account.interfaces.facade.web.model.response.AccountVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.lalifeier.mall.account.applicaiton.service.AccountApplicationService;
import com.github.lalifeier.mall.account.interfaces.facade.web.model.request.RegisterRequest;
import com.github.lalifeier.mall.common.result.Result;

@RestController
@RequestMapping("/api/account")
public class AccountController {
  @Resource
  private AccountApplicationService accountApplicationService;

  // 注册
  @PostMapping("/register")
  public AccountVO register(@Validated @RequestBody RegisterRequest request) {
    return accountApplicationService.register(request);
  }

  // 登录
  @PostMapping("/login")
  public AccountVO login(@Validated @RequestBody LoginRequest request) {
    return accountApplicationService.login(request);
  }

  // 修改个人信息
  @PostMapping("/update")
  public Result<Object> updateUser() {
    return Result.success();
  }

  // 更改密码
  public Result<Object> changePassword() {
    return Result.success();
  }

  // 重置密码
  public Result<Object> resetPassword() {
    return Result.success();
  }

}
