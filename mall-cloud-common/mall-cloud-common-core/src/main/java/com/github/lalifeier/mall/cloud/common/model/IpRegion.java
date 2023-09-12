package com.github.lalifeier.mall.cloud.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IpRegion {

  private String country;
  private String region;
  private String province;
  private String city;
  private String isp;

  public IpRegion(String province, String city) {
    this.province = province;
    this.city = city;
  }
}
