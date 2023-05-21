package com.github.lalifeier.mall.mybatisplus.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.dialects.MySqlDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
@Configuration
//@MapperScan("com.github.lalifeier.mall.demo.infrastructure")
public class MybatisPlusConfig {
  @Bean
  public MybatisPlusInterceptor mybatisPlusInterceptor() {
    MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
    // 添加乐观锁插件
    interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());

    // 添加分页插件
    interceptor.addInnerInterceptor(new PaginationInnerInterceptor(new MySqlDialect()));

    // 数据权限
    //DataPermissionInterceptor dataPermissionInterceptor = new DataPermissionInterceptor();
    //dataPermissionInterceptor.setDataPermissionHandler(new MybatisPlusDataPermissionHandler());
    //interceptor.addInnerInterceptor(dataPermissionInterceptor);

    return interceptor;
  }

}
