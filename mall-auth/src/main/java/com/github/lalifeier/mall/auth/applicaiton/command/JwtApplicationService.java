package com.github.lalifeier.mall.auth.applicaiton;

import com.github.lalifeier.mall.common.domain.User;

public interface JwtApplicationService {

    String generateToken(String username, String password) throws Exception;

    String generateToken(String username, String password, int expire) throws Exception;

    String registerUserAndGenerateToken(User user) throws Exception;
}
