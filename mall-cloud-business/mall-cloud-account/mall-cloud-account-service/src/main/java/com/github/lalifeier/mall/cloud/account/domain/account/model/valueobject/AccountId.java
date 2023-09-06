package com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject;

import com.github.lalifeier.mall.cloud.common.model.marker.Identifier;
import jakarta.validation.ValidationException;
import lombok.Getter;

@Getter
public class AccountId implements Identifier<Long> {
    private final Long value;

    public AccountId(final Long value) {
        if (value == null) {
            throw new ValidationException("账号id不能为空");
        }
        this.value = value;
    }
}
