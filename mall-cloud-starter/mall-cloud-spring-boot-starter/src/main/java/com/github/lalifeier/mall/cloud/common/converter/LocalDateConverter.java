package com.github.lalifeier.mall.cloud.common.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

public class LocalDateConverter extends BaseDateConverter<LocalDate> implements Converter<String, LocalDate> {
  protected static final Map<String, String> FORMAT = new LinkedHashMap(2);

  static {
    FORMAT.put("yyyy-MM-dd", "^\\d{4}-\\d{1,2}-\\d{1,2}$");
    FORMAT.put("yyyy/MM/dd", "^\\d{4}/\\d{1,2}/\\d{1,2}$");
  }

  @Override
  protected Map<String, String> getFormat() {
    return FORMAT;
  }

  @Override
  public LocalDate convert(String source) {
    return super.convert(source, (key) -> LocalDate.parse(source, DateTimeFormatter.ofPattern(key)));
  }
}