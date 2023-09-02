package com.github.lalifeier.mall.cloud.account.applicaiton.authentication.provider;

import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.command.LoginCommand;
import com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.command.UsernameLoginCommand;
import com.github.lalifeier.mall.cloud.account.domain.account.model.entity.Account;
import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.AccountName;
import com.github.lalifeier.mall.cloud.account.domain.account.repository.AccountRepository;
import com.github.lalifeier.mall.cloud.account.infrastructure.enums.LoginErrorCodeEnum;
import com.github.lalifeier.mall.cloud.account.infrastructure.enums.LoginTypeEnum;
import com.github.lalifeier.mall.cloud.account.infrastructure.exception.LoginException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UsernameLoginProvider extends AbstractLoginProvider {
  private final AccountRepository accountRepository;

  @Override
  protected void preAuthenticationCheck(LoginCommand loginCommand) {
    UsernameLoginCommand usernameLoginDTO = (UsernameLoginCommand) loginCommand;
    String username = usernameLoginDTO.getUsername();
    String password = usernameLoginDTO.getPassword();

    if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
      throw new LoginException(LoginErrorCodeEnum.B_USER_PASSWORD_NOT_EXIST);
    }
  }

  @Override
  protected Account authenticate(LoginCommand loginCommand) {
    UsernameLoginCommand usernameLoginDTO = (UsernameLoginCommand) loginCommand;
    String username = usernameLoginDTO.getUsername();
    String password = usernameLoginDTO.getPassword();

    Account account = accountRepository.findByUsername(new AccountName(username));
    if (account == null) {
      throw new LoginException(LoginErrorCodeEnum.B_USER_NOT_EXIST);
    }

    if (!account.getPassword().verifyPassword(password)) {
      throw new LoginException(LoginErrorCodeEnum.B_USER_PASSWORD_ERROR);
    }

    return account;
  }

  @Override
  public boolean supports(LoginTypeEnum loginTypeEnum) {
    return loginTypeEnum.equals(LoginTypeEnum.USERNAME);
  }
}
