package com.github.lalifeier.mall.cloud.common.config;

import static com.github.lalifeier.mall.cloud.common.utils.DateUtil.DATE_TIME_PATTERN;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.github.lalifeier.mall.cloud.common.jackson.JacksonModule;
import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> builder.locale(Locale.CHINA)
                // 时区
                .timeZone(TimeZone.getTimeZone(ZoneId.systemDefault()))
                // Date参数日期格式
                .simpleDateFormat(DATE_TIME_PATTERN)
                // 在序列化时排除空值
                .serializationInclusion(Include.NON_NULL)
                // 序列化和反序列化对象中的所有字段并忽略它们的可见性修饰符
                //                        .visibility(PropertyAccessor.ALL, Visibility.ANY)
                .featuresToDisable(
                        // 不将日期写为时间戳
                        SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
                        // 忽略未知属性
                        DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                        // 对象属性为空时可以序列化
                        SerializationFeature.FAIL_ON_EMPTY_BEANS)
                .modules(new ParameterNamesModule(), new Jdk8Module(), new JavaTimeModule(), new JacksonModule());
    }

    //    @Bean
    //    @Primary
    //    @ConditionalOnMissingBean(ObjectMapper.class)
    //    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
    //        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
    //
    //        objectMapper.registerModule(new JacksonModule());
    //        objectMapper.registerModule(new ParameterNamesModule());
    //        objectMapper.registerModule(new Jdk8Module());
    //        //            objectMapper.registerModule(new JavaTimeModule());
    //
    //        objectMapper.findAndRegisterModules();
    //
    //        return objectMapper;
    //    }
}
