package com.github.lalifeier.mall.cloud.common.config;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.github.lalifeier.mall.cloud.common.enums.BaseEnum;
import com.github.lalifeier.mall.cloud.common.jackson.BaseEnumDeserializer;
import com.github.lalifeier.mall.cloud.common.jackson.BaseEnumSerializer;
import com.github.lalifeier.mall.cloud.common.jackson.SimpleDeserializersWrapper;

@Configuration
// @ConditionalOnClass({Jackson2ObjectMapperBuilder.class, ObjectMapper.class})
public class JacksonConfig {

  /** 默认日期时间格式 */
  public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
  /** 默认日期格式 */
  public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
  /** 默认时间格式 */
  public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

  @Bean
  public Jackson2ObjectMapperBuilderCustomizer customizer() {
    return builder -> {
      // 设置默认的日期时间格式
      builder.locale(Locale.CHINA);
      builder.timeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
      builder.simpleDateFormat(DEFAULT_DATE_TIME_FORMAT);

      // 在序列化时排除空值
      builder.serializationInclusion(JsonInclude.Include.NON_NULL);

      // 设置属性的可见性为任意
      builder.visibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

      // 创建一个 SimpleModule 用于自定义序列化器
      SimpleModule simpleModule = new SimpleModule();
      simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
      simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
      simpleModule.addSerializer(BigInteger.class, ToStringSerializer.instance);
      simpleModule.addSerializer(BigDecimal.class, ToStringSerializer.instance);

      // 创建一个 JavaTimeModule 用于日期和时间的序列化和反序列化
      JavaTimeModule javaTimeModule = new JavaTimeModule();
      DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT);
      javaTimeModule.addSerializer(LocalDateTime.class,
          new LocalDateTimeSerializer(dateTimeFormatter));
      javaTimeModule.addDeserializer(LocalDateTime.class,
          new LocalDateTimeDeserializer(dateTimeFormatter));

      DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);
      javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter));
      javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter));

      DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT);
      javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(timeFormatter));
      javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(timeFormatter));

      // 将 SimpleModule 和 JavaTimeModule 注册到 builder 中
      builder.timeZone(TimeZone.getDefault());
      builder.modules(simpleModule, javaTimeModule);
    };
  }

  @Bean
  public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
    SimpleModule simpleModule = new SimpleModule();
    SimpleDeserializersWrapper deserializers = new SimpleDeserializersWrapper();
    simpleModule.setDeserializers(deserializers);

    simpleModule.addSerializer(BaseEnum.class, BaseEnumSerializer.INSTANCE);
    simpleModule.addDeserializer(BaseEnum.class, BaseEnumDeserializer.INSTANCE);

    ObjectMapper objectMapper = builder.createXmlMapper(false).build();
    objectMapper.registerModule(simpleModule);
    return objectMapper;
  }
}
