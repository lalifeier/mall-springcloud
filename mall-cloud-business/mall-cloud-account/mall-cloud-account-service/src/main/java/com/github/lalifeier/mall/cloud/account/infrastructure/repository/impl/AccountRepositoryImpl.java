package com.github.lalifeier.mall.cloud.account.infrastructure.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.lalifeier.mall.cloud.account.domain.account.model.entity.Account;
import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.AccountId;
import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.AccountName;
import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.Email;
import com.github.lalifeier.mall.cloud.account.domain.account.model.valueobject.PhoneNumber;
import com.github.lalifeier.mall.cloud.account.domain.account.repository.AccountRepository;
import com.github.lalifeier.mall.cloud.account.infrastructure.persistence.mybatis.converter.AccountConverter;
import com.github.lalifeier.mall.cloud.account.infrastructure.persistence.mybatis.mapper.AccountUserMapper;
import com.github.lalifeier.mall.cloud.account.infrastructure.persistence.mybatis.po.AccountUserPO;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    @Resource private AccountUserMapper accountUserMapper;

    private final AccountConverter accountConverter;

    public AccountRepositoryImpl() {
        this.accountConverter = AccountConverter.INSTANCE;
    }

    @Override
    public void attach(@NotNull Account aggregate) {}

    @Override
    public void detach(@NotNull Account aggregate) {}

    @Override
    public Account find(@NotNull AccountId accountId) {
        Long id = accountId.getValue();
        AccountUserPO accountUserPO = accountUserMapper.selectById(id);
        return accountConverter.toData(accountUserPO);
    }

    @Override
    public List<Account> find(Set<AccountId> accountIds) {
        return null;
    }

    @Override
    public void remove(@NotNull Account aggregate) {
        AccountUserPO accountUserPO = accountConverter.fromData(aggregate);
        accountUserMapper.deleteById(accountUserPO);
    }

    @Override
    public void save(@NotNull Account aggregate) {
        AccountUserPO accountUserPO = accountConverter.fromData(aggregate);

        if (aggregate.getId() != null && aggregate.getId().getValue() > 0) {
            accountUserMapper.updateById(accountUserPO);
        } else {
            accountUserMapper.insert(accountUserPO);
            aggregate.setId(new AccountId(accountUserPO.getId()));
        }
    }

    @Override
    public Account findByUsername(AccountName name) {
        LambdaQueryWrapper<AccountUserPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(AccountUserPO::getUsername, name.getValue());
        AccountUserPO accountUserPO = accountUserMapper.selectOne(queryWrapper);
        return accountConverter.toData(accountUserPO);
    }

    @Override
    public Account findByEmail(Email email) {
        LambdaQueryWrapper<AccountUserPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(AccountUserPO::getEmail, email.getValue());
        AccountUserPO accountUserPO = accountUserMapper.selectOne(queryWrapper);
        return accountConverter.toData(accountUserPO);
    }

    @Override
    public Account findByPhone(PhoneNumber phone) {
        LambdaQueryWrapper<AccountUserPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(AccountUserPO::getPhone, phone.getValue());
        AccountUserPO accountUserPO = accountUserMapper.selectOne(queryWrapper);
        return accountConverter.toData(accountUserPO);
    }

    @Override
    public Long countByUserNameOrEmailOrPhone(AccountName name, Email email, PhoneNumber phone) {
        LambdaQueryWrapper<AccountUserPO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper
                .or()
                .eq(AccountUserPO::getUsername, name.getValue())
                .or()
                .eq(AccountUserPO::getEmail, email.getValue())
                .or()
                .eq(AccountUserPO::getPhone, phone.getValue());
        return accountUserMapper.selectCount(queryWrapper);
    }
}
