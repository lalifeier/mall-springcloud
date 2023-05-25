object BuildPlugins {
//  val gradle by lazy { "com.android.tools.build:gradle:${Versions.gradlePlugin}" }
//  val kotlinGradle by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}" }
//  val safeArgs by lazy { "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.safeArgs}" }

//  val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
}

object Dependencies {
  const val lombok = "org.projectlombok:lombok:${Versions.lombokVersion}"
  const val dubbo = "org.apache.dubbo:dubbo-spring-boot-starter:${Versions.dubboVersion}"
  const val grpcNettyShaded = "io.grpc:grpc-netty-shaded:${Versions.grpcVersion}" // gRPC 核心库
  const val grpcProtobuf = "io.grpc:grpc-protobuf:${Versions.grpcVersion}" // gRPC Protocol Buffers 序列化支持
  const val grpcStub = "io.grpc:grpc-stub:${Versions.grpcVersion}" // gRPC Stub 和客户端库
  const val grpcSpringBoot = "net.devh:grpc-spring-boot-starter:${Versions.grpcSpringBootVersion}"
  const val springBootAdmin = "de.codecentric:spring-boot-admin-starter-server:${Versions.springBootAdminVersion}"
  const val springSecurityOauth2 = "org.springframework.security.oauth:spring-security-oauth2:${Versions.springSecurityOauth2Version}"
  const val springCloudOauth2 = "org.springframework.cloud:spring-cloud-starter-oauth2:${Versions.springCloudOauth2Version}"
  const val springAuthorizationServer = "org.springframework.security:spring-security-oauth2-authorization-server:${Versions.springAuthorizationServerVersion}"
  const val mapstruct = "org.mapstruct:mapstruct:${Versions.mapstructVersion}"
  const val mapstructProcessor = "org.mapstruct:mapstruct-processor:${Versions.mapstructVersion}"
  const val nimbusJoseJwt = "com.nimbusds:nimbus-jose-jwt:${Versions.nimbusJoseJwtVersion}"
  const val pagehelperStarter = "com.github.pagehelper:pagehelper-spring-boot-starter:${Versions.pagehelperVersion}"
  const val pagehelper = "com.github.pagehelper:pagehelper:${Versions.pagehelperVersion}"
  const val mybatisPlus = "com.baomidou:mybatis-plus-boot-starter:${Versions.mybatisPlusVersion}"
  const val mybatisPlusJoin = "com.github.yulichang:mybatis-plus-join-boot-starter:${Versions.mybatisPlusJoinVersion}"
  const val mybatisPlusGenerator = "com.baomidou:mybatis-plus-generator:${Versions.mybatisPlusGeneratorVersion}"
  const val freemarker = "org.freemarker:freemarker:${Versions.freemarkerVersion}"
  const val druid = "com.alibaba:druid-spring-boot-starter:${Versions.druidVersion}"
  const val mysqlConnector = "mysql:mysql-connector-java:${Versions.mysqlConnectorVersion}"
  const val shardingsphere = "org.apache.shardingsphere:shardingsphere-jdbc-core-spring-boot-starter:${Versions.shardingsphereVersion}"
  const val hutool = "cn.hutool:hutool-all:${Versions.hutoolVersion}"
  const val springdoc = "org.springdoc:springdoc-openapi-ui:${Versions.springdocVersion}"
  const val knife4j = "com.github.xiaoymin:knife4j-springdoc-ui:${Versions.knife4jVersion}"
  const val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"
  const val fastjson = "com.alibaba:fastjson:${Versions.fastjsonVersion}"
  const val guava = "com.google.guava:guava:${Versions.guavaVersion}"
  const val commonsLang3 = "org.apache.commons:commons-lang3:${Versions.commonsLang3Version}"
  const val commonsIo = "commons-io:commons-io:${Versions.commonsIoVersion}"
  const val commonsCodec = "commons-codec:commons-codec:${Versions.commonsCodecVersion}"
  const val commonsBeanutils = "commons-beanutils:commons-beanutils:${Versions.commonsBeanutilsVersion}"
  const val commonsCollections4 = "org.apache.commons:commons-collections4:${Versions.commonsCollections4Version}"
  const val logstash = "net.logstash.logback:logstash-logback-encoder:${Versions.logstashVersion}"
  const val skywalking = "org.apache.skywalking:apm-toolkit-logback-1.x:${Versions.skywalkingVersion}"
  const val skywalkingTrace = "org.apache.skywalking:apm-toolkit-trace:${Versions.skywalkingVersion}"
  const val caffeine = "com.github.ben-manes.caffeine:caffeine:${Versions.caffeineVersion}"
  const val xxlJob = "com.xuxueli:xxl-job-core:${Versions.xxlJobVersion}"
  const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttpVersion}"
  const val retrofit = "com.github.lianjiatech:retrofit-spring-boot-starter:${Versions.retrofitVersion}"
  const val feignOkhttp = "io.github.openfeign:feign-okhttp:${Versions.feignOkhttpVersion}"
  const val aliyunSdkOss = "com.aliyun.oss:aliyun-sdk-oss:${Versions.aliyunSdkOssVersion}"
  const val micrometerRegistryPrometheus = "io.micrometer:micrometer-registry-prometheus:${Versions.micrometerRegistryPrometheusVersion}"
  const val saToken = "cn.dev33:sa-token-spring-boot-starter:${Versions.saTokenVersion}"
  const val wechatpay = "com.github.wechatpay-apiv3:wechatpay-java:${Versions.wechatpayVersion}"
  const val alipay = "com.alipay.sdk:alipay-sdk-java:${Versions.alipayVersion}"

  val all by lazy {
    listOf(lombok, dubbo, grpcNettyShaded, grpcProtobuf, grpcStub, grpcSpringBoot, springBootAdmin,
      springSecurityOauth2, springCloudOauth2, springAuthorizationServer,
      mapstruct, mapstructProcessor,
      nimbusJoseJwt,
      pagehelperStarter, pagehelper,
      mybatisPlus, mybatisPlusJoin, mybatisPlusGenerator, freemarker,
      druid, mysqlConnector, shardingsphere,
      hutool,
      springdoc, knife4j,
      gson, fastjson, guava,
      commonsLang3, commonsIo, commonsIo, commonsCodec, commonsBeanutils, commonsCollections4,
      logstash, skywalking, skywalkingTrace, caffeine, xxlJob,
      okhttp, retrofit, feignOkhttp, aliyunSdkOss, micrometerRegistryPrometheus, saToken,
      wechatpay, alipay
    )
  }
}
