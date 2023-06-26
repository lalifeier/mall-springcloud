package com.github.lalifeier.mall.cloud.mybatisplus.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.dialects.MySqlDialect;
import com.github.lalifeier.mall.cloud.mybatisplus.injector.MySqlInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
public class MybatisPlusConfig {


  @Bean
  public MybatisPlusInterceptor mybatisPlusInterceptor() {
    MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

    // 添加分页插件
    interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
    // 添加乐观锁插件
    interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());

    // 数据权限
    //DataPermissionInterceptor dataPermissionInterceptor = new DataPermissionInterceptor();
    //dataPermissionInterceptor.setDataPermissionHandler(new MybatisPlusDataPermissionHandler());
    //interceptor.addInnerInterceptor(dataPermissionInterceptor);

    return interceptor;
  }

  @Bean
  public MySqlInjector mysqlInjector() {
    return new MySqlInjector();
  }
}
