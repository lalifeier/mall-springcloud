package com.github.lalifeier.mall.cloud.log.configure;

import com.github.lalifeier.mall.cloud.log.factory.ParseFunctionFactory;
import com.github.lalifeier.mall.cloud.log.function.DefaultParseFunction;
import com.github.lalifeier.mall.cloud.log.function.ParseFunction;
import com.github.lalifeier.mall.cloud.log.properties.LogRecordProperties;
import com.github.lalifeier.mall.cloud.log.service.FunctionService;
import com.github.lalifeier.mall.cloud.log.service.LogRecordService;
import com.github.lalifeier.mall.cloud.log.service.OperatorService;
import com.github.lalifeier.mall.cloud.log.service.impl.DefaultFunctionServiceImpl;
import com.github.lalifeier.mall.cloud.log.service.impl.DefaultLogRecordServiceImpl;
import com.github.lalifeier.mall.cloud.log.service.impl.DefaultOperatorServiceImpl;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

@Slf4j
@Configuration
@EnableConfigurationProperties({LogRecordProperties.class})
public class LogRecordAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean(FunctionService.class)
  public FunctionService functionService(ParseFunctionFactory parseFunctionFactory) {
    return new DefaultFunctionServiceImpl(parseFunctionFactory);
  }

  @Bean
  public ParseFunctionFactory parseFunctionFactory(@Autowired List<ParseFunction> parseFunctions) {
    return new ParseFunctionFactory(parseFunctions);
  }

  @Bean
  @ConditionalOnMissingBean(ParseFunction.class)
  public DefaultParseFunction parseFunction() {
    return new DefaultParseFunction();
  }

  @Bean
  @ConditionalOnMissingBean(OperatorService.class)
  @Role(BeanDefinition.ROLE_APPLICATION)
  public OperatorService operatorService() {
    return new DefaultOperatorServiceImpl();
  }

  @Bean
  @ConditionalOnMissingBean(LogRecordService.class)
  @Role(BeanDefinition.ROLE_APPLICATION)
  public LogRecordService recordService() {
    return new DefaultLogRecordServiceImpl();
  }
}
