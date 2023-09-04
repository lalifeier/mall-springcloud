package com.github.lalifeier.mall.cloud.enums.service;

import com.github.lalifeier.mall.cloud.enums.model.EnumDefinition;

import java.util.List;

public interface EnumService {
  List<EnumDefinition> getAllEnums();

  EnumDefinition getEnumByName(String name);
}
