FROM openjdk:8-jdk-alpine
LABEL maintainer=lalifeier@gmail.com

# 设置时区
ENV TIME_ZONE=Asia/Shanghai
RUN ln -sf /usr/share/zoneinfo/$TIME_ZONE /etc/localtime \
    && echo $TIME_ZONE > /etc/timezone

# 下载 SkyWalking APM 的 Java Agent
ENV SKYWALKING_AGENT_VERSION=8.12.0
RUN wget -O skywalking-agent.tar.gz "https://dlcdn.apache.org/skywalking/java-agent/${SKYWALKING_AGENT_VERSION}/apache-skywalking-agent-${SKYWALKING_AGENT_VERSION}.tar.gz" \
    && tar -zxvf skywalking-agent.tar.gz \
    && cp skywalking-agent/optional-plugins/apm-spring-webflux-5.x-plugin-${SKYWALKING_AGENT_VERSION}.jar skywalking-agent/plugins/ \
    && cp skywalking-agent/optional-plugins/apm-spring-cloud-gateway-2.1.x-plugin-${SKYWALKING_AGENT_VERSION}.jar skywalking-agent/plugins/ \
    && cp skywalking-agent/optional-plugins/apm-spring-cloud-gateway-3.x-plugin-${SKYWALKING_AGENT_VERSION}.jar skywalking-agent/plugins/ \
    && mv skywalking-agent /opt/skywalking-agent \
    && rm -rf skywalking-agent.tar.gz

# 复制应用程序的 Jar 包
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# 设置环境变量
ENV JAVA_OPTS=""
ENV SKYWALKING_AGENT_SERVICE_NAME=""
ENV SKYWALKING_COLLECTOR_BACKEND_SERVICE="127.0.0.1:11800"
ENV NACOS_SERVER_ADDR="localhost:8848"
ENV NACOS_NAMESPACE="dev"
ENV AGENT_OPTS="-Dskywalking.agent.service_name=$SKYWALKING_AGENT_SERVICE_NAME \
    -Dskywalking.collector.backend_service=$SKYWALKING_COLLECTOR_BACKEND_SERVICE \
    -DNACOS_SERVER_ADDR=$NACOS_SERVER_ADDR \
    -DNACOS_NAMESPACE=$NACOS_NAMESPACE"

# 启动应用程序
ENTRYPOINT ["sh","-c","java $JAVA_OPTS \
    -javaagent:/opt/skywalking-agent/skywalking-agent.jar \
    $AGENT_OPTS \
    -jar /app.jar"]

#-javaagent:/opt/skywalking-agent/skywalking-agent.jar -DSW_AGENT_NAME=mall_auth -DSW_AGENT_COLLECTOR_BACKEND_SERVICES=127.0.0.1:11800
