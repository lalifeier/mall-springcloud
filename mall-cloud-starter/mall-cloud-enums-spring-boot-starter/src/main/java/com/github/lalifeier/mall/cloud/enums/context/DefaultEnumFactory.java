package com.github.lalifeier.mall.cloud.enums.context;

import com.github.lalifeier.mall.cloud.enums.annocation.EnumInfo;
import com.github.lalifeier.mall.cloud.enums.model.EnumDefinition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultEnumFactory implements EnumDefinitionRegistry {

  private Map<String, EnumDefinition> enumDefinitionMap;

  public DefaultEnumFactory() {
    this.enumDefinitionMap = new HashMap<>();
  }

  @Override
  public void registerEnumDefinition(Class<?> enumClass) {
    EnumInfo enumInfo = enumClass.getAnnotation(EnumInfo.class);

    if (enumInfo != null) {
      EnumDefinition enumDefinition = new EnumDefinition();
      enumDefinition.setName(enumInfo.name());
      enumDefinition.setKey(enumInfo.key());
      enumDefinition.setDescription(enumInfo.description());

      enumDefinitionMap.put(enumDefinition.getName(), enumDefinition);
    }
  }

  @Override
  public EnumDefinition getEnumDefinition(String name) {
    return enumDefinitionMap.get(name);
  }

  @Override
  public List<EnumDefinition> getAllEnumDefinitions() {
    return new ArrayList<>(enumDefinitionMap.values());
  }
}
