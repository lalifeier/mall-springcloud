package com.github.lalifeier.mall.admin.interfaces.rest;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
//    @Autowired
//    UserApplicationService userApplicationService;

    //@Autowired
    //UserQueryApplicationService userQueryApplicationService;
    //
    //@GetMapping
    //public List<User> query() {
    //    return null;
    //}

    //@GetMapping("/currentUser")
    //public User currentUser(HttpServletRequest request) {
    //    // 从Header中获取用户信息
    //    String userStr = request.getHeader("user");
    //    JSONObject userJsonObject = new JSONObject(userStr);
    //    return User.builder()
    //            .username(userJsonObject.getStr("user_name"))
    //            .id(Convert.toLong(userJsonObject.get("id")))
    //            .roles(Convert.toList(String.class, userJsonObject.get("authorities"))).build();
    //}

    //@GetMapping
    //public JSONObject findUser(HttpServletRequest request) {
    //    //从Header中获取用户信息
    //    String userStr = request.getHeader("user");
    //    return new JSONObject(userStr);
    //}

}
