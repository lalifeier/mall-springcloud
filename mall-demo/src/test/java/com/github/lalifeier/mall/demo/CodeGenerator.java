package com.github.lalifeier.mall.demo;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.function.Consumer;

public class CodeGenerator {
    private static String projectPath = System.getProperty("user.dir");
    private static String dbUrl = "jdbc:mysql://localhost:3306/mall?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useSSL=false";
    private static String dbUser = "root";
    private static String dbPassword = "123456";

    @Test
    public void generatorCode() {
        FastAutoGenerator.create(datasourceBuilder())
                // 全局配置
                .globalConfig(globalConfigConsumer())
                // 包配置
                .packageConfig(packageConfigConsumer())
                // 策略配置
                .strategyConfig(strategyConfigConsumer())
                // 模板配置
                .templateEngine(templateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                // 注入配置
                // .injectionConfig(injectionConfigConsumer())
                .execute();
    }

    //数据库配置
    public static DataSourceConfig.Builder datasourceBuilder() {
        return new DataSourceConfig.Builder(dbUrl, dbUser, dbPassword);

    }

    //全局配置
    public static Consumer<GlobalConfig.Builder> globalConfigConsumer() {
        return builder -> {
            builder.author("lalifeier") // 设置作者
                    // .enableSwagger() // 开启 swagger 模式
                    .disableOpenDir() // 执行完毕不打开文件夹
                    //.fileOverride() // 覆盖已生成文件
                    .outputDir(projectPath + "/src/test/java"); // 指定输出目录
        };
    }

    //包配置
    public static Consumer<PackageConfig.Builder> packageConfigConsumer() {
        return builder -> {
            builder.parent("com.github.lalifeier.mall.demo") // 设置父包名
                    .moduleName("demo")  // 设置父包模块名
                    .controller("controller") //生成controller层
                    .entity("entity") //生成实体层
                    .service("service") //生成服务层
                    .serviceImpl("service.impl")
                    .mapper("mapper") //生成mapper层
                    .pathInfo(Collections.singletonMap(OutputFile.xml, projectPath + "/src/test/resources/mapper")); // 设置mapperXml生成路径
        };
    }


    //策略配置
    public static Consumer<StrategyConfig.Builder> strategyConfigConsumer() {
        return builder -> {
            builder.addInclude("user") // 设置需要生成的表名
                    .addTablePrefix("tbl_")// 设置过滤表前缀
                    .serviceBuilder() //开启service策略配置
                    .formatServiceFileName("%sService") //取消Service前的I
                    .controllerBuilder() //开启controller策略配置
                    .enableRestStyle() //配置restful风格
                    .enableHyphenStyle() //url中驼峰转连字符
                    .entityBuilder() //开启实体类配置
                    .enableLombok() //开启lombok
                    .enableChainModel(); //开启lombok链式操作
        };
    }

    //模板配置
    public static AbstractTemplateEngine templateEngine() {
        return new FreemarkerTemplateEngine();
    }

    //注入配置
    public static Consumer<InjectionConfig.Builder> injectionConfigConsumer() {
        return builder -> {
            builder.beforeOutputFile((tableInfo, objectMap) -> {
                System.out.println("tableInfo: " + tableInfo.getEntityName() + " objectMap: " + objectMap.size());
            });
        };
    }
}


