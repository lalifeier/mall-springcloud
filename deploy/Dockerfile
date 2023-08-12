#FROM openjdk:17-jdk-alpine
FROM apache/skywalking-java-agent:8.16.0-java17
LABEL maintainer=lalifeier@gmail.com

# 设置时区
ENV TIME_ZONE=Asia/Shanghai
RUN ln -sf /usr/share/zoneinfo/$TIME_ZONE /etc/localtime \
    && echo $TIME_ZONE > /etc/timezone

# 复制应用程序的 Jar 包
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

# 设置环境变量
ENV JAVA_OPTS="-Xms4g -Xmx4g -XX:NewRatio=1 -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m -XX:+AlwaysPreTouch -XX:+UseConcMarkSweepGC -XX:CMSInitiatingOccupancyFraction=75 -XX:+UseCMSInitiatingOccupancyOnly -XX:+ExplicitGCInvokesConcurrent -XX:+ParallelRefProcEnabled -XX:+CMSParallelInitialMarkEnabled -XX:MaxTenuringThreshold=3 -XX:+UnlockDiagnosticVMOptions -XX:ParGCCardsPerStrideChunk=1024 -Xloggc:./logs/gc-myapp.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintPromotionFailure -XX:+PrintGCApplicationStoppedTime -XX:-UseBiasedLocking -XX:AutoBoxCacheMax=20000 -Djava.security.egd=file:/dev/./urandom -XX:+PrintCommandLineFlags -XX:-OmitStackTraceInFastThrow -XX:ErrorFile=./logs/hs_err_%p.log -Djava.rmi.server.hostname=127.0.0.1 -Dcom.sun.management.jmxremote.port=7001 -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Djava.net.preferIPv4Stack=true -Dfile.encoding=UTF-8"
ENV SKYWALKING_AGENT_SERVICE_NAME=""
ENV SKYWALKING_COLLECTOR_BACKEND_SERVICE="localhost:11800"
ENV NACOS_SERVER_ADDR="localhost:8848"
ENV NACOS_NAMESPACE="dev"
ENV AGENT_OPTS="-Dskywalking.agent.service_name=$SKYWALKING_AGENT_SERVICE_NAME \
    -Dskywalking.collector.backend_service=$SKYWALKING_COLLECTOR_BACKEND_SERVICE \
    -DNACOS_SERVER_ADDR=$NACOS_SERVER_ADDR \
    -DNACOS_NAMESPACE=$NACOS_NAMESPACE"

# 启动应用程序
ENTRYPOINT ["sh","-c","java $JAVA_OPTS \
    -javaagent:/skywalking/agent/skywalking-agent.jar \
    $AGENT_OPTS \
    -jar /app.jar"]

#-javaagent:/skywalking/agent/skywalking-agent.jar -DSW_AGENT_NAME=mall_auth -DSW_AGENT_COLLECTOR_BACKEND_SERVICES=localhost:11800
