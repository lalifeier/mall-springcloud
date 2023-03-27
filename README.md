# 项目介绍

基于 Spring Cloud Alibaba 的微服务架构，使用 Java 和 Kotlin 语言编写，集成了多个优秀的开源库和框架来提供丰富的功能。

# 技术栈

## 语言

- [Java](https://www.java.com)：一种广泛使用的计算机编程语言，具有面向对象、平台无关性和安全性等特点。

- [Kotlin](https://github.com/JetBrains/kotlin)：一种现代化的静态语言，可以与 Java 无缝交互。

## 框架

- [Spring Boot](https://github.com/spring-projects/spring-boot)：快速构建 Java 应用程序的框架。
- [Spring Cloud](https://github.com/spring-cloud/spring-cloud)：用于构建分布式系统的工具集合。
- [Spring Cloud Alibaba](https://github.com/alibaba/spring-cloud-alibaba)：在 Spring Cloud 基础上提供更多阿里云生态支持的工具集。

## 库

- [Lombok](https://github.com/rzwitserloot/lombok)：简化 Java 代码的实用工具库。
- [SLF4J API](http://www.slf4j.org/)：Java 日志框架，提供通用的日志 API，可以透明地与各种日志系统进行交互。
- [Spring Security OAuth2](https://github.com/spring-projects/spring-security-oauth)：基于 OAuth2 协议的安全框架，可用于保护 RESTful Web 服务。
- [Spring Cloud Starter OAuth2](https://github.com/spring-cloud/spring-cloud-starter-oauth2)：使用 Spring Cloud 框架构建 OAuth2 安全服务的入门依赖。
- [Spring Authorization Server](https://github.com/spring-projects-experimental/spring-authorization-server)：一个实验性的项目，提供 OAuth2 授权服务器实现。
- [Dubbo](https://github.com/apache/dubbo)：高性能的分布式服务框架，由阿里巴巴开发。
- [Spring Boot Admin](https://github.com/codecentric/spring-boot-admin)：管理和监控 Spring Boot 应用程序的界面。
- [MapStruct](https://github.com/mapstruct/mapstruct)：Java 注解处理器，用于生成类型安全的映射代码。
- [Nimbus JOSE JWT](https://connect2id.com/products/nimbus-jose-jwt)：Java 库，用于处理 JSON Web Token（JWT）和相关标准。
- [PageHelper Spring Boot Starter](https://github.com/pagehelper/pagehelper-spring-boot)：用于在 Spring Boot 应用程序中启用数据库分页功能的依赖。
- [MyBatis Plus Boot Starter](https://github.com/baomidou/mybatis-plus)：MyBatis 框架的增强版，简化了数据访问层的开发。
- [MyBatis Plus Join Boot Starter](https://github.com/yulichang/mybatis-plus-join)：MyBatis Plus 的扩展，提供对 SQL 联接操作的支持。
- [MyBatis Plus Generator](https://github.com/baomidou/mybatis-plus)：使用 MyBatis Plus 框架从数据库表格生成 Java 代码的自动生成器。
- [Freemarker](https://github.com/apache/freemarker)：模板引擎，用于生成动态 HTML 页面。
- [Druid Spring Boot Starter](https://github.com/alibaba/druid)：数据库连接池和监控工具，用于 Spring Boot 应用程序。
- [MySQL Connector Java](https://dev.mysql.com/downloads/connector/j/)：官方的 MySQL JDBC 驱动程序。
- [ShardingSphere JDBC Core Spring Boot Starter](https://github.com/apache/shardingsphere)：ShardingSphere 数据库分片框架的入门依赖。
- [Hutool](https://github.com/dromara/hutool)：实用工具库，提供各种常用的 Java 函数和类。
- [Springdoc OpenAPI UI](https://github.com/springdoc/springdoc-openapi)：根据 Spring Boot 应用程序生成 OpenAPI 规范的 API 文档。
- [Springdoc OpenAPI Webflux UI](https://github.com/springdoc/springdoc-openapi)：为响应式 Web 应用程序定制的 Springdoc OpenAPI UI 变种。
- [Knife4j](https://github.com/xiaoymin/swagger-bootstrap-ui)：Swagger Bootstrap UI 的替代品，提供更多功能，如 Mock API 测试等。
- [GSON](https://github.com/google/gson)：Java JSON 序列化和反序列化库，由 Google 提供。
- [FastJSON](https://github.com/alibaba/fastjson)：高性能 JSON 序列化/反序列化库，由阿里巴巴提供。
- [Guava](https://github.com/google/guava)：Google 提供的 Java 基础 API 库。
- [Commons Lang3](https://github.com/apache/commons-lang)：Jakarta 组织提供的 Java 常用工具类库。
- [Commons IO](https://commons.apache.org/proper/commons-io/)：Jakarta 组织提供的 Java 文件和流操作类库。
- [Commons Codec](https://commons.apache.org/proper/commons-codec/)：Jakarta 组织提供的 Java 编码和解码算法类库。
- [Commons BeanUtils](https://commons.apache.org/proper/commons-beanutils/)：Jakarta 组织提供的 Java 反射工具类库。
- [Commons Collections4](https://commons.apache.org/proper/commons-collections/)：Jakarta 组织提供的 Java 数据结构和算法类库。
- [Logstash Logback Encoder](https://github.com/logstash/logstash-logback-encoder)：Logback 框架的编码器，用于格式化日志输出，以便被 Logstash 等日志收集系统解析。
- [Skywalking APM Toolkit Logback 1.x](https://github.com/apache/skywalking)：Skywalking 分布式应用性能监控系统的 Logback 插件。
- [Caffeine](https://github.com/ben-manes/caffeine)：高性能的本地缓存库，支持多种缓存策略和过期时间控制。
- [XXL-Job](https://github.com/xuxueli/xxl-job)：分布式任务调度平台，提供 web 界面用于任务管理和监控。
- [OkHttp](https://square.github.io/okhttp/)：Java 的 HTTP 客户端库，支持同步和异步请求，以及拦截器、缓存等功能。
- [Retrofit Spring Boot Starter](https://github.com/lianjiatech/retrofit-spring-boot-starter)：使用 Retrofit 的入门依赖，可轻松将 RESTful Web 服务绑定到 Java 接口上。
- [FeignOkHttp](https://github.com/OpenFeign/feign)：基于 OkHttp 实现的 Feign 扩展，提供更好的性能和稳定性。
- [Aliyun SDK OSS](https://github.com/aliyun/aliyun-oss-java-sdk)：阿里云对象存储 OSS 的 Java SDK，用于方便地上传和下载文件。
- [Micrometer Registry Prometheus](https://micrometer.io/docs/registry/prometheus)：Prometheus 注册表的 Micrometer 实现，用于监控应用程序指标。

## 插件

- [Gradle Docker 插件](https://github.com/Transmode/gradle-docker-plugin)：用于将 Java 应用程序打包为 Docker 镜像的 Gradle 插件。

# 如何运行

您可以按照以下步骤在本地运行该项目：

1.克隆项目到本地：`git clone https://github.com/lalifeier/mall-springcloud.git`

2.进入项目根目录，并执行以下命令启动环境：

```bash
cd mall-springcloud
docker-compose up -f deploy/docker-compose-env.yaml -d
```

3.在项目根目录下运行以下命令安装依赖并打包：

```bash
./gradlew clean build
```

4.在项目根目录下运行以下命令启动项目：

```bash
java -jar mall-gateway/build/libs/mall-gateway-x.x.x.jar
java -jar mall-auth/build/libs/mall-auth-x.x.x.jar
java -jar mall-admin/build/libs/mall-admin-x.x.x.jar
```

其中 `x.x.x` 为版本号。

如果您想要打包成 Docker 镜像并运行，可以执行以下步骤：

1.在项目根目录下运行以下命令构建 Docker 镜像：

```bash
./gradlew clean build dockerBuildImage
```

2.运行以下命令启动 Docker 容器：

```bash
docker-compose up -f deploy/docker-compose-app.yaml -d
```

希望这些信息对您有所帮助。
