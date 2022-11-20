package com.github.lalifeier.mall.auth.interfaces.facade.rest;

import com.github.lalifeier.mall.auth.applicaiton.service.command.UserCommandApplicationService;
import com.github.lalifeier.mall.auth.applicaiton.service.query.UserQueryApplicationService;
import com.github.lalifeier.mall.auth.interfaces.converter.UserConverter;
import com.github.lalifeier.mall.auth.interfaces.model.req.*;
import com.github.lalifeier.result.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {


    private final UserConverter userConverter;
    private final UserCommandApplicationService userCommandApplicationService;
    private final UserQueryApplicationService userQueryApplicationService;

    public UserController(UserConverter userConverter, UserCommandApplicationService userCommandApplicationService, UserQueryApplicationService userQueryApplicationService) {
        this.userConverter = userConverter;
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
