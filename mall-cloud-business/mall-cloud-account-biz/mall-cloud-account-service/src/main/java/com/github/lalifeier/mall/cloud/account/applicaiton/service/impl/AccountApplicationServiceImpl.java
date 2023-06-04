package com.github.lalifeier.mall.cloud.account.applicaiton.service.impl;

import com.github.lalifeier.mall.cloud.account.applicaiton.command.impl.LoginCommandHandler;
import com.github.lalifeier.mall.cloud.account.applicaiton.command.impl.RegisterCommandHandler;
import com.github.lalifeier.mall.cloud.account.applicaiton.service.AccountApplicationService;
import com.github.lalifeier.mall.cloud.account.domain.account.repository.AccountRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountApplicationServiceImpl implements AccountApplicationService {

  @Resource
  private RegisterCommandHandler registerCommandHandler;

  @Resource
  private LoginCommandHandler loginCommandHandler;

  @Resource
  private AccountRepository accountRepository;

  //@Override
  //public RegisterResponseVO register(RegisterRequest request) {
  //  return registerCommandHandler.execute(request);
  //}


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
