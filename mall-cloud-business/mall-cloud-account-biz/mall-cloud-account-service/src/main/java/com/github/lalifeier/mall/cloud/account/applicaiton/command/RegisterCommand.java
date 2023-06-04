package com.github.lalifeier.mall.cloud.account.applicaiton.command;

import com.github.lalifeier.mall.cloud.account.applicaiton.dto.AccountBO;
import com.github.lalifeier.mall.cloud.account.applicaiton.dto.RegisterBO;

public interface RegisterCommand {
  AccountBO execute(RegisterBO registerBO);
}
