package com.github.lalifeier.mall.cloud.cache.listener;

import lombok.Data;

import java.io.Serializable;

@Data
public class CacheMessage implements Serializable {
  private String cacheName;
  private Object key;
  private Object value;
  private Integer type;
}
