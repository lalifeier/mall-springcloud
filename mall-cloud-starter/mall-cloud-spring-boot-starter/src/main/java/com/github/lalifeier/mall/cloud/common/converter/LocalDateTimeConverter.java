package com.github.lalifeier.mall.cloud.common.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

public class LocalDateTimeConverter extends BaseDateConverter<LocalDateTime> implements Converter<String, LocalDateTime> {

  protected static final Map<String, String> FORMAT = new LinkedHashMap<>(4);

  static {
    FORMAT.put("yyyy-MM-dd HH:mm:ss.SSS", "^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{2}:\\d{2}:\\d{2}.\\d{1,3}$");
    FORMAT.put("yyyy-MM-dd HH:mm:ss", "^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{2}:\\d{2}:\\d{2}$");
    FORMAT.put("yyyy/MM/dd HH:mm:ss.SSS", "^\\d{4}/\\d{1,2}/\\d{1,2} {1}\\d{2}:\\d{2}:\\d{2}.\\d{1,3}$");
    FORMAT.put("yyyy/MM/dd HH:mm:ss", "^\\d{4}/\\d{1,2}/\\d{1,2} {1}\\d{2}:\\d{2}:\\d{2}$");
  }

  @Override
  protected Map<String, String> getFormat() {
    return FORMAT;
  }

  @Override
  public LocalDateTime convert(String source) {
    return super.convert(source,
      (key) -> LocalDateTime.parse(source, DateTimeFormatter.ofPattern(key)));
  }
}
