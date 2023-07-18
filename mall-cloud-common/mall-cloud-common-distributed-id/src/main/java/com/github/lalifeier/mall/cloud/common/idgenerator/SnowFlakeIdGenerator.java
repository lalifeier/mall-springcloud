package com.github.lalifeier.mall.cloud.common.idgenerator;

public class SnowFlakeIdGenerator implements IdGenerator {
  private SnowFlake snowFlake;

  public SnowFlakeIdGenerator(long dataCenterId, long machineId) {
    this.snowFlake = new SnowFlake(dataCenterId, machineId);
  }

  @Override
  public long generateId() {
    return this.snowFlake.nextId();
  }
}
