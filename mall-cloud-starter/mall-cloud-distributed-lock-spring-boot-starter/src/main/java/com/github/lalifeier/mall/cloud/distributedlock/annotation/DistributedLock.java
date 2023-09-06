package com.github.lalifeier.mall.cloud.distributedlock.annotation;

import com.github.lalifeier.mall.cloud.distributedlock.enums.LockTypeEnum;
import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributedLock {

    /** 锁的资源前缀 */
    String prefix() default "lock";

    /** 锁的资源 */
    String field() default "default";

    /** 获取锁的最大尝试时间 该值大于0则使用 locker.tryLock 方法加锁，否则使用 locker.lock 方法 */
    long waitTime() default 0;

    /** 加锁的时间，超过这个时间后锁便自动解锁 如果leaseTime为-1，则保持锁定直到显式解锁 */
    long leaseTime() default -1;

    /** 参数的时间单位 */
    TimeUnit unit() default TimeUnit.SECONDS;

    /** 锁类型，默认为可重入锁 */
    LockTypeEnum lockType() default LockTypeEnum.REENTRANT_LOCK;
}
