package com.github.lalifeier.mall.cloud.auth.interfaces.facade.rest;

import com.github.lalifeier.mall.cloud.auth.applicaiton.service.command.UserCommandApplicationService;
import com.github.lalifeier.mall.cloud.auth.applicaiton.service.query.UserQueryApplicationService;
import com.github.lalifeier.mall.cloud.auth.interfaces.model.req.*;
import com.github.lalifeier.mall.cloud.common.result.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    //private UserConverter userConverter;
    private final UserCommandApplicationService userCommandApplicationService;
    private final UserQueryApplicationService userQueryApplicationService;

    public UserController(UserCommandApplicationService userCommandApplicationService, UserQueryApplicationService userQueryApplicationService) {
        this.userCommandApplicationService = userCommandApplicationService;
        this.userQueryApplicationService = userQueryApplicationService;
    }


    @PostMapping("/register")
    public Result<Object> register(@RequestBody RegisterReq registerReq) {
        return Result.success();
    }

    @PostMapping("/login")
    public Result<Object> login(@RequestBody LoginReq loginReq) {
        return Result.success();
    }

    @PostMapping("/")
    public Result<Object> create(@RequestBody CreateUserReq createUserReq) {
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result<Object> update(@PathVariable("id") Long id, @RequestBody UpdateUserReq userReq) {
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Object> delete(@PathVariable("id") Long id) {
        return Result.success();
    }

    @GetMapping("/list")
    public void list(@RequestParam ListUserReq listUserReq) {

    }

    @GetMapping("/")
    public void pageList(@RequestParam PageListUserReq pageListUserReq) {

    }

    @GetMapping("/{id}")
    public Result<Object> get(@PathVariable("id") Long id) {
        return Result.success();
    }
}
