package com.github.lalifeier.mall.cloud.account.interfaces.rest;


import com.github.lalifeier.mall.cloud.account.api.AuthenticationApi;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.command.LoginCommand;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.dto.LoginDTO;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.service.AuthenticationApplicationService;
import com.github.lalifeier.mall.cloud.account.interfaces.rest.converter.AuthenticationConverter;
import com.github.lalifeier.mall.cloud.account.interfaces.rest.factory.LoginCommandFactory;
import com.github.lalifeier.mall.cloud.account.model.request.LoginRequest;
import com.github.lalifeier.mall.cloud.account.model.request.RegisterRequest;
import com.github.lalifeier.mall.cloud.account.model.response.LoginResponse;
import com.github.lalifeier.mall.cloud.account.model.response.RegisterResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AuthenticationController implements AuthenticationApi {

  private final AuthenticationApplicationService authenticationApplicationService;


  private final AuthenticationConverter authenticationConverter = AuthenticationConverter.INSTANCE;

  public AuthenticationController(AuthenticationApplicationService authenticationApplicationService) {
    this.authenticationApplicationService = authenticationApplicationService;
  }

  @Override
  public LoginResponse login(LoginRequest request) {
    LoginCommand loginCommand = LoginCommandFactory.getLoginCommand(request);
    LoginDTO loginDTO = authenticationApplicationService.login(loginCommand);
    return authenticationConverter.toVO(loginDTO);
  }

  @Override
  public RegisterResponse register(RegisterRequest request) {
    return null;
  }
}
