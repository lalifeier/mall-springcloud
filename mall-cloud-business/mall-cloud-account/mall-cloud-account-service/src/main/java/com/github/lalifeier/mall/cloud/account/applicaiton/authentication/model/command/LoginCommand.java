package com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.command;

import com.github.lalifeier.mall.cloud.account.infrastructure.enums.LoginTypeEnum;
import lombok.Data;

@Data
public abstract class LoginCommand {
    public abstract LoginTypeEnum getLoginType();
}
