package com.github.lalifeier.mall.cloud.common.jackson.module;

import static com.github.lalifeier.mall.cloud.common.utils.DateUtil.*;

import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import java.io.Serial;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * java 8 时间默认序列化
 */
public class JavaTimeModule extends SimpleModule {
    @Serial
    private static final long serialVersionUID = 1L;

    public JavaTimeModule() {
        super(PackageVersion.VERSION);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        this.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
        this.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        this.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter));
        this.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter));

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(TIME_PATTERN);
        this.addSerializer(LocalTime.class, new LocalTimeSerializer(timeFormatter));
        this.addDeserializer(LocalTime.class, new LocalTimeDeserializer(timeFormatter));
    }
}
