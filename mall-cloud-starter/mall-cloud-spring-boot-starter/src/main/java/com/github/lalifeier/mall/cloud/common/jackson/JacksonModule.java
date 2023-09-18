package com.github.lalifeier.mall.cloud.common.jackson;

import static com.github.lalifeier.mall.cloud.common.utils.DateUtil.*;

import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import java.io.Serial;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class JacksonModule extends SimpleModule {
    @Serial
    private static final long serialVersionUID = 1L;

    public JacksonModule() {
        super(PackageVersion.VERSION);

        // Long 和 BigInteger 采用定制的逻辑序列化，避免超过js的精度
        this.addSerializer(Long.class, ToStringSerializer.instance);
        this.addSerializer(Long.TYPE, ToStringSerializer.instance);
        this.addSerializer(BigInteger.class, ToStringSerializer.instance);
        // BigDecimal 采用 toString 避免精度丢失，前端采用 decimal.js 来计算
        this.addSerializer(BigDecimal.class, ToStringSerializer.instance);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        this.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
        this.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        this.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter));
        this.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter));

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(TIME_PATTERN);
        this.addSerializer(LocalTime.class, new LocalTimeSerializer(timeFormatter));
        this.addDeserializer(LocalTime.class, new LocalTimeDeserializer(timeFormatter));

        this.setDeserializers(new SimpleDeserializersWrapper());
        this.addSerializer(Enum.class, EnumSerializer.INSTANCE);
        this.addDeserializer(Enum.class, EnumDeserializer.INSTANCE);
    }
}
