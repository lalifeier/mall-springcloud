package com.github.lalifeier.mall.account.applicaiton.service.impl;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

import com.github.lalifeier.mall.account.domain.account.model.entity.Account;
import com.github.lalifeier.mall.account.domain.account.model.valueobject.AccountName;
import com.github.lalifeier.mall.account.domain.account.model.valueobject.AccountPassword;
import com.github.lalifeier.mall.account.domain.account.repository.AccountRepository;
import com.github.lalifeier.mall.account.infrastructure.error.LoginErrorCodes;
import com.github.lalifeier.mall.account.infrastructure.error.LoginException;
import org.springframework.stereotype.Service;

import com.github.lalifeier.mall.account.applicaiton.command.impl.LoginCommandHandler;
import com.github.lalifeier.mall.account.applicaiton.command.impl.RegisterCommandHandler;
import com.github.lalifeier.mall.account.applicaiton.service.AccountApplicationService;
import com.github.lalifeier.mall.account.interfaces.dto.request.LoginRequest;
import com.github.lalifeier.mall.account.interfaces.dto.request.RegisterRequest;
import com.github.lalifeier.mall.account.interfaces.dto.response.LoginResponse;
import com.github.lalifeier.mall.account.interfaces.dto.response.RegisterResponse;

@Service
public class AccountApplicationServiceImpl implements AccountApplicationService {

  @Resource
  private RegisterCommandHandler registerCommandHandler;

  @Resource
  private LoginCommandHandler loginCommandHandler;

  @Resource
  private AccountRepository accountRepository;

  @Override
  public RegisterResponse register(RegisterRequest request) {
    return registerCommandHandler.execute(request);
  }

  @Override
  public LoginResponse login(LoginRequest request) {
    return loginCommandHandler.execute(request);
  }


  //public boolean changePassword(@NotNull AccountName name, @NotNull AccountPassword oldPassword,
  //                              @NotNull AccountPassword newPassword) {
  //  Account account = accountRepository.find(name);
  //  if (account == null) {
  //    throw new LoginException(LoginErrorCodes.USER_NOT_EXIST);
  //  }
  //
  //  if (!account.getPassword().verifyPassword(oldPassword.getEncryptPassword())) {
  //    throw new LoginException(LoginErrorCodes.PASSWORD_ERROR);
  //  }
  //
  //  account.setPassword(newPassword);
  //
  //  accountRepository.save(account);
  //
  //  return true;
  //}
}
