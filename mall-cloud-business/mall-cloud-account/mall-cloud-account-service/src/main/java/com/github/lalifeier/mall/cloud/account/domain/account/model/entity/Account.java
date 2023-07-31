package com.github.lalifeier.mall.cloud.account.domain.account.model.entity;

import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.*;
import com.github.lalifeier.mall.cloud.common.model.marker.Aggregate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Account implements Aggregate<AccountId> {
    private AccountId id;
    private AccountName username;
    private AccountPassword password;
    private Email email;
    private PhoneNumber phone;
    //  private StatusEnum status;

    @Override
    public AccountId getId() {
        return id;
    }
}
