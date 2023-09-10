package com.github.lalifeier.mall.cloud.mybatisplus.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DataPermissionInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.github.lalifeier.mall.cloud.mybatisplus.handler.DefaultMetaObjectHandler;
import com.github.lalifeier.mall.cloud.mybatisplus.handler.EnumTypeHandler;
import com.github.lalifeier.mall.cloud.mybatisplus.handler.MybatisPlusDataPermissionHandler;
import com.github.lalifeier.mall.cloud.mybatisplus.injector.MySqlInjector;

@Configuration
@EnableTransactionManagement
public class MybatisPlusAutoConfiguration {

  @Bean
  public ConfigurationCustomizer configurationCustomizer() {
    return configuration -> {
      configuration.setDefaultEnumTypeHandler(EnumTypeHandler.class);
    };
  }

  @Bean
  @ConditionalOnMissingBean
  public MybatisPlusInterceptor mybatisPlusInterceptor() {
    MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
    // 分页插件
    interceptor.addInnerInterceptor(paginationInnerInterceptor());
    // 乐观锁插件
    interceptor.addInnerInterceptor(optimisticLockerInnerInterceptor());
    // 数据权限
    // interceptor.addInnerInterceptor(dataPermissionInterceptor());

    return interceptor;
  }

  /** 分页插件 */
  public PaginationInnerInterceptor paginationInnerInterceptor() {
    PaginationInnerInterceptor paginationInnerInterceptor =
        new PaginationInnerInterceptor(DbType.MYSQL);
    // 设置最大单页限制数量，默认 500 条，-1 不受限制
    paginationInnerInterceptor.setMaxLimit(-1L);
    // 分页合理化
    paginationInnerInterceptor.setOverflow(true);
    return paginationInnerInterceptor;
  }

  /** 乐观锁插件 */
  public OptimisticLockerInnerInterceptor optimisticLockerInnerInterceptor() {
    return new OptimisticLockerInnerInterceptor();
  }

  /** 数据权限拦截器 */
  public DataPermissionInterceptor dataPermissionInterceptor() {
    DataPermissionInterceptor dataPermissionInterceptor = new DataPermissionInterceptor();
    dataPermissionInterceptor.setDataPermissionHandler(new MybatisPlusDataPermissionHandler());
    return dataPermissionInterceptor;
  }

  /** 元对象字段填充控制器 */
  @Bean
  public MetaObjectHandler metaObjectHandler() {
    return new DefaultMetaObjectHandler();
  }

  @Bean
  public MySqlInjector mysqlInjector() {
    return new MySqlInjector();
  }
}
