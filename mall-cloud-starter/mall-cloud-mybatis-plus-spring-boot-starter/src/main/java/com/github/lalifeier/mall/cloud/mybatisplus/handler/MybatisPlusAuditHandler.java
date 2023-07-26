package com.github.lalifeier.mall.cloud.mybatisplus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.github.lalifeier.mall.cloud.common.context.UserContextUtil;
import com.github.lalifeier.mall.cloud.mybatisplus.po.BasePO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class MybatisPlusAuditHandler implements MetaObjectHandler {


  @Override
  public void insertFill(MetaObject metaObject) {
    log.info("start insert fill ....");

    if (!shouldFill(metaObject)) {
      return;
    }

    LocalDateTime now = LocalDateTime.now();
    Long userId = getUserId();

    this.strictInsertFill(metaObject, "createdAt", LocalDateTime.class, now);
//    this.strictInsertFill(metaObject, "createdBy", Long.class, userId);
    this.strictInsertFill(metaObject, "updatedAt", LocalDateTime.class, now);
//    this.strictInsertFill(metaObject, "updatedBy", Long.class, userId);
  }

  @Override
  public void updateFill(MetaObject metaObject) {
    log.info("start update fill ....");

    if (!shouldFill(metaObject)) {
      return;
    }

    Long userId = getUserId();

    this.strictUpdateFill(metaObject, "updatedAt", LocalDateTime.class, LocalDateTime.now());
//    this.strictInsertFill(metaObject, "updatedBy", Long.class, userId);
  }

  private boolean shouldFill(MetaObject metaObject) {
    return metaObject.getOriginalObject() instanceof BasePO;
  }

  public Long getUserId() {
    return UserContextUtil.getUserId();
  }
}
