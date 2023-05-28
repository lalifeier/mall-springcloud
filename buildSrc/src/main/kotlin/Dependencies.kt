object Dependencies {
  const val springBoot = "org.springframework.boot:spring-boot-dependencies:${Versions.springBoot}"
  const val springCloud = "org.springframework.cloud:spring-cloud-dependencies:${Versions.springCloud}"
  const val springCloudAlibaba = "com.alibaba.cloud:spring-cloud-alibaba-dependencies:${Versions.springCloudAlibaba}"

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
  const val commonsCollections4 = "org.apache.commons:commons-collections4:${Versions.commonsCollections4}"
  const val logstash = "net.logstash.logback:logstash-logback-encoder:${Versions.logstash}"
  const val skywalking = "org.apache.skywalking:apm-toolkit-logback-1.x:${Versions.skywalking}"
  const val skywalkingTrace = "org.apache.skywalking:apm-toolkit-trace:${Versions.skywalking}"
  const val caffeine = "com.github.ben-manes.caffeine:caffeine:${Versions.caffeine}"
  const val xxlJob = "com.xuxueli:xxl-job-core:${Versions.xxlJob}"
  const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
  const val retrofit = "com.github.lianjiatech:retrofit-spring-boot-starter:${Versions.retrofit}"
  const val feignOkhttp = "io.github.openfeign:feign-okhttp:${Versions.feignOkhttp}"
  const val aliyunSdkOss = "com.aliyun.oss:aliyun-sdk-oss:${Versions.aliyunSdkOss}"
  const val micrometerRegistryPrometheus = "io.micrometer:micrometer-registry-prometheus:${Versions.micrometerRegistryPrometheus}"
  const val saToken = "cn.dev33:sa-token-spring-boot-starter:${Versions.saToken}"
  const val wechatpay = "com.github.wechatpay-apiv3:wechatpay-java:${Versions.wechatpay}"
  const val alipay = "com.alipay.sdk:alipay-sdk-java:${Versions.alipay}"

  const val queryDslApt = "com.querydsl:querydsl-apt:${Versions.queryDsl}"
  const val queryDslJpa = "com.querydsl:querydsl-jpa:${Versions.queryDsl}"

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
      wechatpay, alipay,
      queryDslApt, queryDslJpa
    )
  }
}
