package com.github.lalifeier.mall.demo.infrastructure.component;

import java.time.LocalDateTime;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MybatisPlusAuditHandler implements MetaObjectHandler {

  @Override
  public void insertFill(MetaObject metaObject) {
    log.info("start insert fill ....");

    LocalDateTime now = LocalDateTime.now();
    Long userId = getUserId();
    log.info("userId: {}", userId);

    this.strictInsertFill(metaObject, "createdAt", LocalDateTime.class, now);
    //this.strictInsertFill(metaObject, "createdBy", Long.class, userId);
    this.strictInsertFill(metaObject, "updatedAt", LocalDateTime.class, now);
    //this.strictInsertFill(metaObject, "updatedBy", Long.class, userId);
  }

  @Override
  public void updateFill(MetaObject metaObject) {
    log.info("start update fill ....");
    Long userId = getUserId();

    this.strictUpdateFill(metaObject, "updatedAt", LocalDateTime.class, LocalDateTime.now());
    //this.strictInsertFill(metaObject, "updatedBy", Long.class, userId);
  }

  public Long getUserId() {
    Long userId = null;
    return userId;
  }
}
