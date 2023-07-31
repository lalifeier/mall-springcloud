package com.github.lalifeier.mall.cloud.common.converter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.core.convert.converter.Converter;

public class LocalTimeConverter extends BaseDateConverter<LocalTime>
        implements Converter<String, LocalTime> {
    protected static final Map<String, String> FORMAT = new LinkedHashMap(2);

    static {
        FORMAT.put("HH:mm:ss", "^\\d{1,2}:\\d{2}:\\d{2}$");
        FORMAT.put("HH:mm:ss.SSS", "^\\d{1,2}:\\d{2}:\\d{2}.\\d{1,3}$");
    }

    @Override
    protected Map<String, String> getFormat() {
        return FORMAT;
    }

    @Override
    public LocalTime convert(String source) {
        return super.convert(
                source, (key) -> LocalTime.parse(source, DateTimeFormatter.ofPattern(key)));
    }
}
