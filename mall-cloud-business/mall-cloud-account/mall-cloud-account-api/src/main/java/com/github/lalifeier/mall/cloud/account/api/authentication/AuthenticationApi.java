package com.github.lalifeier.mall.cloud.account.api.authentication;

import com.github.lalifeier.mall.cloud.account.api.authentication.model.command.RegisterCommand;
import com.github.lalifeier.mall.cloud.account.api.authentication.model.dto.LoginDTO;
import com.github.lalifeier.mall.cloud.account.api.authentication.model.dto.RegisterDTO;
import com.github.lalifeier.mall.cloud.account.api.authentication.model.request.LoginRequest;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Validated
public interface AuthenticationApi {
    @PostMapping("/login")
    LoginDTO login(@Valid @RequestBody LoginRequest request);

    @PostMapping("/register")
    RegisterDTO register(@Valid @RequestBody RegisterCommand command);
}
