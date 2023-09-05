package com.github.lalifeier.mall.cloud.enums.context;

import com.github.lalifeier.mall.cloud.enums.model.EnumDefinition;
import java.util.List;

public interface EnumDefinitionRegistry {
  void registerEnumDefinition(Class<?> enumClass);

  EnumDefinition getEnumDefinition(String name);

  List<EnumDefinition> getAllEnumDefinitions();
}
