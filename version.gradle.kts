object Versions {
  const val projectVersion = "1.0.0-SNAPSHOT"
  const val springBootVersion = "2.6.13"
  const val springCloudVersion = "2021.0.5"
  const val springCloudAlibabaVersion = "2021.0.5.0"
  const val kotlinVersion = "1.6.10"
  const val gradleDockerPluginVersion = "6.7.0"
  const val lombokVersion = "1.18.24"
  const val sl4jApiVersion = "2.0.6"
  const val dubboVersion = "3.1.1"
  const val grpcVersion = "1.55.1"
  const val protobufVersion = "3.23.0"
  const val protobufGradlePluginVersion = "0.9.3"
  const val grpcSpringBootVersion = "2.13.1.RELEASE"
  const val springBootAdminVersion = "2.6.9"
  const val springSecurityOauth2Version = "2.5.2.RELEASE"
  const val springCloudOauth2Version = "2.2.5.RELEASE"
  const val springAuthorizationServerVersion = "0.3.1"
  const val mapstructVersion = "1.5.3.Final"
  const val nimbusJoseJwtVersion = "9.25.2"
  const val pagehelperStarterVersion = "1.4.5"
  const val pagehelperVersion = "5.3.2"
  const val mybatisPlusVersion = "3.5.2"
  const val mybatisPlusJoinVersion = "1.3.9"
  const val mybatisPlusGeneratorVersion = "3.5.2"
  const val freemarkerVersion = "2.3.31"
  const val druidVersion = "1.2.12"
  const val mysqlConnectorVersion = "8.0.30"
  const val shardingsphereVersion = "4.1.1"
  const val hutoolVersion = "5.8.6"
  const val springdocVersion = "1.6.11"
  const val knife4jVersion = "3.0.3"
  const val gsonVersion = "2.9.1"
  const val fastjsonVersion = "2.0.15"
  const val guavaVersion = "31.1-jre"
  const val commonsLang3Version = "3.12.0"
  const val commonsIoVersion = "2.11.0"
  const val commonsCodecVersion = "1.15"
  const val commonsBeanutilsVersion = "1.9.4"
  const val commonsCollections4Version = "4.4"
  const val logstashVersion = "7.2"
  const val skywalkingVersion = "8.12.0"
  const val caffeineVersion = "2.9.3"
  const val xxlJobVersion = "2.3.1"
  const val okhttpVersion = "4.10.0"
  const val retrofitVersion = "3.0.0"
  const val feignOkhttpVersion = "12.1"
  const val aliyunSdkOssVersion = "3.15.1"
  const val micrometerRegistryPrometheusVersion = "1.10.2"
  const val saTokenVersion = "1.34.0"
  const val wechatpayVersion = "0.2.7"
}

object Dependencies {
  const val lombok = "org.projectlombok:lombok:${Versions.lombok}"
  const val dubbo = "org.apache.dubbo:dubbo-spring-boot-starter:${Versions.dubbo}"
  const val grpcNettyShaded = "io.grpc:grpc-netty-shaded:${Versions.grpc}" // gRPC 核心库
  const val grpcProtobuf = "io.grpc:grpc-protobuf:${Versions.grpc}" // gRPC Protocol Buffers 序列化支持
  const val grpcStub = "io.grpc:grpc-stub:${Versions.grpc}" // gRPC Stub 和客户端库
  const val grpcSpringBoot = "net.devh:grpc-spring-boot-starter:${Versions.grpcSpringBoot}"
  const val springBootAdmin = "de.codecentric:spring-boot-admin-starter-server:${Versions.springBootAdmin}"
  const val springSecurityOauth2 = "org.springframework.security.oauth:spring-security-oauth2:${Versions.springSecurityOauth2}"
  const val springCloudOauth2 = "org.springframework.cloud:spring-cloud-starter-oauth2:${Versions.springCloudOauth2}"
  const val springAuthorizationServer = "org.springframework.security:spring-security-oauth2-authorization-server:${Versions.springAuthorizationServer}"
  const val mapstruct = "org.mapstruct:mapstruct:${Versions.mapstruct}"
  const val mapstructProcessor = "org.mapstruct:mapstruct-processor:${Versions.mapstruct}"
  const val nimbusJoseJwt = "com.nimbusds:nimbus-jose-jwt:${Versions.nimbusJoseJwt}"
  const val pagehelperStarter = "com.github.pagehelper:pagehelper-spring-boot-starter:${Versions.pagehelper}"
  const val pagehelper = "com.github.pagehelper:pagehelper:${Versions.pagehelper}"
  const val mybatisPlus = "com.baomidou:mybatis-plus-boot-starter:${Versions.mybatisPlus}"
  const val mybatisPlusJoin = "com.github.yulichang:mybatis-plus-join-boot-starter:${Versions.mybatisPlusJoin}"
  const val mybatisPlusGenerator = "com.baomidou:mybatis-plus-generator:${Versions.mybatisPlusGenerator}"
  const val freemarker = "org.freemarker:freemarker:${Versions.freemarker}"
  const val druid = "com.alibaba:druid-spring-boot-starter:${Versions.druid}"
  const val mysqlConnector = "mysql:mysql-connector-java:${Versions.mysqlConnector}"
  const val shardingsphere = "org.apache.shardingsphere:shardingsphere-jdbc-core-spring-boot-starter:${Versions.shardingsphere}"
  const val hutool = "cn.hutool:hutool-all:${Versions.hutool}"
  const val springdoc = "org.springdoc:springdoc-openapi-ui:${Versions.springdoc}"
  const val knife4j = "com.github.xiaoymin:knife4j-springdoc-ui:${Versions.knife4j}"
  const val gson = "com.google.code.gson:gson:${Versions.gson}"
  const val fastjson = "com.alibaba:fastjson:${Versions.fastjson}"
  const val guava = "com.google.guava:guava:${Versions.guava}"
  const val commonsLang3 = "org.apache.commons:commons-lang3:${Versions.commonsLang3}"
  const val commonsIo = "commons-io:commons-io:${Versions.commonsIo}"
  const val commonsCodec = "commons-codec:commons-codec:${Versions.commonsCodec}"
  const val commonsBeanutils = "commons-beanutils:commons-beanutils:${Versions.commonsBeanutils}"
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
