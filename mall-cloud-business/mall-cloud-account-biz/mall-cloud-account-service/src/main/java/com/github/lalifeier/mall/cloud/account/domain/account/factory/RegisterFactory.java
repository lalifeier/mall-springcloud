package com.github.lalifeier.mall.cloud.account.domain.account.factory;

import com.github.lalifeier.mall.cloud.account.domain.account.strategy.RegisterStrategy;
import com.github.lalifeier.mall.cloud.account.domain.account.strategy.impl.EmailRegisterStrategy;
import com.github.lalifeier.mall.cloud.account.domain.account.strategy.impl.PhoneRegisterStrategy;
import com.github.lalifeier.mall.cloud.account.domain.account.strategy.impl.UsernameRegisterStrategy;
import com.github.lalifeier.mall.cloud.account.infrastructure.enums.RegisterType;

public class RegisterFactory {
  public static RegisterStrategy createRegisterStrategy(RegisterType type) {
    return switch (type) {
      case USERNAME -> new UsernameRegisterStrategy();
      case EMAIL -> new EmailRegisterStrategy();
      case PHONE -> new PhoneRegisterStrategy();
      default -> throw new RuntimeException("不支持的注册方式");
    };
  }
}
