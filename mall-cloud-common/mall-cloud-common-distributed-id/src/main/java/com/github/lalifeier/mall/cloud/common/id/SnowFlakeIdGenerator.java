package com.github.lalifeier.mall.cloud.common.id;

public class SnowFlakeIdGenerator implements IdGenerator {
  private static SnowflakeIdWorker snowflakeIdWorker;

  public SnowFlakeIdGenerator() {
    long workerId = Long.parseLong(System.getProperty("workerId", "0"));
    long datacenterId = Long.parseLong(System.getProperty("datacenterId", "0"));
    snowflakeIdWorker = new SnowflakeIdWorker(workerId, datacenterId);
  }

  SnowFlakeIdGenerator(long workerId, long datacenterId) {
    snowflakeIdWorker = new SnowflakeIdWorker(workerId, datacenterId);
  }

  @Override
  public long generateId() {
    return snowflakeIdWorker.nextId();
  }
}
