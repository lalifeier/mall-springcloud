package com.github.lalifeier.mall.mybatisplus.handler;

import com.baomidou.mybatisplus.core.plugins.InterceptorIgnoreHelper;
import com.baomidou.mybatisplus.extension.plugins.handler.DataPermissionHandler;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;

@Slf4j
public class MybatisPlusDataPermissionHandler implements DataPermissionHandler {

  @Override
  public Expression getSqlSegment(Expression where, String mappedStatementId) {
    log.info("数据权限处理器===========");
    log.info("{}", where);
    log.info("{}", mappedStatementId);
    log.info("是否忽略:{}", InterceptorIgnoreHelper.willIgnoreDataPermission(mappedStatementId));


    return where;
  }
}
