package com.github.lalifeier.mall.cloud.account.applicaiton.authentication.model.command;

import com.github.lalifeier.mall.cloud.account.infrastructure.enums.RegisterType;
import lombok.Data;

@Data
public abstract class RegisterCommand {
    public abstract RegisterType getRegisterType();
}
