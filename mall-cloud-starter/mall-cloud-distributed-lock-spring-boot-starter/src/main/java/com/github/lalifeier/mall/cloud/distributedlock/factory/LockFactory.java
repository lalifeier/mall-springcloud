package com.github.lalifeier.mall.cloud.distributedlock.factory;

import com.github.lalifeier.mall.cloud.distributedlock.enums.LockTypeEnum;
import com.github.lalifeier.mall.cloud.distributedlock.exception.LockException;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;

public class LockFactory {
  private RedissonClient redissonClient;

  public LockFactory(RedissonClient redissonClient) {
    this.redissonClient = redissonClient;
  }

  public RLock getLock(LockTypeEnum lockType, String lockKey) {
    RLock rLock = null;
    switch (lockType) {
      case FAIR_LOCK:
        rLock = redissonClient.getFairLock(lockKey);
        break;
      case REENTRANT_LOCK:
        rLock = redissonClient.getLock(lockKey);
        break;
      case READ_LOCK:
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock(lockKey);
        rLock = readWriteLock.readLock();
        break;
      case WRITE_LOCK:
        RReadWriteLock rwLock = redissonClient.getReadWriteLock(lockKey);
        rLock = rwLock.writeLock();
        break;
      default:
        throw new LockException("Unsupported lock type: " + lockType);
    }
    return rLock;
  }
}
