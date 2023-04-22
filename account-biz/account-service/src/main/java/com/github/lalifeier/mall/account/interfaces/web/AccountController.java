package com.github.lalifeier.mall.account.interfaces.web;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.lalifeier.mall.account.applicaiton.service.command.AccountCommandApplicationService;
import com.github.lalifeier.mall.common.result.Result;

@RestController
@RequestMapping("/api/account")
public class AccountController {
  @Resource
  private AccountCommandApplicationService accountCommandApplicationService;

  // 注册
  // @PostMapping("/register")
  // public Result<Long> register(@RequestBody RegisterRequest req) {
  // Long id = accountCommandApplicationService.register(req);
  // return Result.success(id);
  // }

  // // 登录
  // @PostMapping("/login")
  // public Result<Object> login(@RequestBody LoginRequest req) {
  // accountCommandApplicationService.login(req);
  // return Result.success();
  // }

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
