package com.github.lalifeier.mall.cloud.mybatisplus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.github.lalifeier.mall.cloud.common.context.UserContextUtil;
import com.github.lalifeier.mall.cloud.common.exception.ServiceException;
import com.github.lalifeier.mall.cloud.mybatisplus.entity.BaseEntity;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DefaultMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        if (!shouldFill(metaObject)) {
            return;
        }

        try {
            BaseEntity baseEntity = (BaseEntity) metaObject.getOriginalObject();
            LocalDateTime now = LocalDateTime.now();
            Long userId = getUserId();
            baseEntity.setCreatedTime(now);
            baseEntity.setUpdatedTime(now);
            baseEntity.setCreatedBy(userId);
            baseEntity.setUpdatedBy(userId);
        } catch (Exception e) {
            throw new ServiceException("自动注入异常 => " + e.getMessage());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (!shouldFill(metaObject)) {
            return;
        }

        try {
            BaseEntity baseEntity = (BaseEntity) metaObject.getOriginalObject();
            LocalDateTime now = LocalDateTime.now();
            Long userId = getUserId();
            baseEntity.setUpdatedTime(now);
            baseEntity.setUpdatedBy(userId);
        } catch (Exception e) {
            throw new ServiceException("自动注入异常 => " + e.getMessage());
        }
    }

    private boolean shouldFill(MetaObject metaObject) {
        return metaObject.getOriginalObject() instanceof BaseEntity;
    }

    public Long getUserId() {
        return UserContextUtil.getUserId();
    }
}
