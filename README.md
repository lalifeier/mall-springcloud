# 项目介绍

这是一个基于 Gradle Kotlin DSL 构建工具的多模块 Spring Cloud Alibaba 微服务架构的项目。该项目使用 Spring Cloud Alibaba 实现了微服务注册、配置中心和服务网关等核心功能，同时使用 Gradle Kotlin DSL 来管理多模块的项目结构，并采用 Java 和 Kotlin 两种语言进行编写。

## 如何运行

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

## Thanks & 感谢

Thanks for [JetBrains](https://www.jetbrains.com/?from=lalifeier) for the wonderful IDE.

<a href="https://www.jetbrains.com/?from=lalifeier"><img src="https://resources.jetbrains.com/storage/products/company/brand/logos/jb_beam.svg" /></a>
