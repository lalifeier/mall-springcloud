package com.github.lalifeier.mall.cloud.distributedlock.enums;

public enum LockTypeEnum {
    REENTRANT_LOCK, // 可重入锁
    FAIR_LOCK, // 公平锁
    READ_LOCK, // 读锁
    WRITE_LOCK, // 写锁
    RED_LOCK, // 红锁
    MULTI_LOCK; // 联锁
}
