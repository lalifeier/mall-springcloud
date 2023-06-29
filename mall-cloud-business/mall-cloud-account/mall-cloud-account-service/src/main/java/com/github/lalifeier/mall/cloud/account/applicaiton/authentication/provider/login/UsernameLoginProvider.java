package com.github.lalifeier.mall.cloud.account.applicaiton.authentication.provider.login;

import com.github.lalifeier.mall.cloud.account.applicaiton.account.dto.AccountDTO;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.command.LoginCommand;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.command.UsernameLoginCommand;
import com.github.lalifeier.mall.cloud.account.domain.account.model.entity.Account;
import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.AccountName;
import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.AccountPassword;
import com.github.lalifeier.mall.cloud.account.domain.account.repository.AccountRepository;
import com.github.lalifeier.mall.cloud.account.infrastructure.enums.LoginType;

import javax.annotation.Resource;

public class UsernameLoginProvider extends AbstractLoginProvider {

  @Resource
  private AccountRepository accountRepository;

  @Override
  protected void preAuthenticationCheck(LoginCommand loginCommand) {

  }

  @Override
  protected AccountDTO authenticate(LoginCommand loginCommand) {
    UsernameLoginCommand usernameLoginDTO = (UsernameLoginCommand) loginCommand;
    String username = usernameLoginDTO.getUsername();
    String password = usernameLoginDTO.getPassword();

    Account account = accountRepository.findByUsername(new AccountName(username));
    if (account == null) {
      throw new RuntimeException("用户名不存在");
    }
    if (!account.getPassword().sameValueAs(new AccountPassword(password))) {
      throw new RuntimeException("用户名或密码错误");
    }

    return null;
  }

  @Override
  public boolean supports(LoginType loginType) {
    return loginType.equals(LoginType.USERNAME);
  }
}
