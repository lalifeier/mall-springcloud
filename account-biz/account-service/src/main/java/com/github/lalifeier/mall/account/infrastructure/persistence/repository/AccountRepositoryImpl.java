package com.github.lalifeier.mall.account.infrastructure.persistence.repository;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lalifeier.mall.common.model.PageList;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import com.github.lalifeier.mall.account.domain.account.model.entity.AccountDO;
import com.github.lalifeier.mall.account.domain.account.model.valueobject.AccountId;
import com.github.lalifeier.mall.account.domain.account.model.valueobject.AccountName;
import com.github.lalifeier.mall.account.domain.account.repository.AccountRepository;
import com.github.lalifeier.mall.account.infrastructure.persistence.mybatis.converter.AccountConverter;
import com.github.lalifeier.mall.account.infrastructure.persistence.mybatis.mapper.AccountUserMapper;
import com.github.lalifeier.mall.account.infrastructure.persistence.mybatis.po.AccountUserPO;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

  @Resource
  private AccountUserMapper accountUserMapper;

  private final AccountConverter accountConverter;

  public AccountRepositoryImpl() {
    this.accountConverter = AccountConverter.INSTANCE;
  }

  @Override
  public void attach(@NotNull AccountDO aggregate) {

  }

  @Override
  public void detach(@NotNull AccountDO aggregate) {

  }

  @Override
  public AccountDO find(@NotNull AccountId accountId) {
    AccountUserPO accountUserPO = accountUserMapper.selectById(accountId.getValue());
    return accountConverter.toAccountDO(accountUserPO);
  }

  @Override
  public AccountDO find(AccountName accountName) {
    AccountUserPO accountUserPO = accountUserMapper.findByUsername(accountName.getValue());
    return accountConverter.toAccountDO(accountUserPO);
  }

  @Override
  public void remove(@NotNull AccountDO aggregate) {
    AccountUserPO accountUserPO = accountConverter.toAccountUserPO(aggregate);
    accountUserMapper.deleteById(accountUserPO);
  }

  @Override
  public void save(@NotNull AccountDO aggregate) {
    AccountUserPO accountUserPO = accountConverter.toAccountUserPO(aggregate);

    if (aggregate.getId() != null && aggregate.getId().getValue() > 0) {
      accountUserMapper.updateById(accountUserPO);
    } else {
      accountUserMapper.insert(accountUserPO);
      aggregate.setId(new AccountId(accountUserPO.getId()));
    }
  }

  public List<AccountDO> list() {
    LambdaQueryWrapper<AccountUserPO> queryWrapper = Wrappers.lambdaQuery();

    // queryWrapper.eq(BookPO::getId, "");
    queryWrapper.orderByDesc(AccountUserPO::getId);

    List<AccountUserPO> accountUserPOList = accountUserMapper.selectList(queryWrapper);

    return accountConverter.toAccountDOList(accountUserPOList);
  }

  public PageList<AccountDO> page(Integer pageNum, Integer pageSize) {
    Page<AccountUserPO> page = new Page<>(pageNum, pageSize);
    LambdaQueryWrapper<AccountUserPO> queryWrapper = Wrappers.lambdaQuery();

    queryWrapper.orderByDesc(AccountUserPO::getId);

    IPage<AccountUserPO> bookPage = accountUserMapper.selectPage(page, queryWrapper);

    List<AccountUserPO> accountUserPOList = bookPage.getRecords();
    List<AccountDO> accountDOList  =accountConverter.toAccountDOList(accountUserPOList);

    //PageList<Book> pageList = new PageList<>();
    //pageList.setData(bookList);
    //pageList.setPageInfo(bookPage);

    return null;
  }
}
