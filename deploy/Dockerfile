#FROM openjdk:17-jdk-alpine
FROM apache/skywalking-java-agent:8.16.0-java17
LABEL maintainer=lalifeier@gmail.com

WORKDIR /app

# 设置时区
ENV TIME_ZONE=Asia/Shanghai
ENV SKYWALKING_AGENT_VERSION=8.16.0
ENV AGENT_PLUGINS_DIR=/skywalking/agent/plugins
ENV AGENT_OPTIONAL_PLUGINS_DIR=/skywalking/agent/optional-plugins

RUN ln -sf /usr/share/zoneinfo/$TIME_ZONE /etc/localtime \
    && echo $TIME_ZONE > /etc/timezone \
    && mv $AGENT_OPTIONAL_PLUGINS_DIR/apm-spring-webflux-5.x-plugin-${SKYWALKING_AGENT_VERSION}.jar $AGENT_PLUGINS_DIR/ \
    && mv $AGENT_OPTIONAL_PLUGINS_DIR/apm-spring-cloud-gateway-3.x-plugin-${SKYWALKING_AGENT_VERSION}.jar $AGENT_PLUGINS_DIR/

# 复制应用程序的 Jar 包
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

# 设置环境变量
ENV JAVA_OPTS="-Xms4g -Xmx4g -XX:NewRatio=1 -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m -XX:+AlwaysPreTouch -XX:+UseConcMarkSweepGC -XX:CMSInitiatingOccupancyFraction=75 -XX:+UseCMSInitiatingOccupancyOnly -XX:+ExplicitGCInvokesConcurrent -XX:+ParallelRefProcEnabled -XX:+CMSParallelInitialMarkEnabled -XX:MaxTenuringThreshold=3 -XX:+UnlockDiagnosticVMOptions -XX:ParGCCardsPerStrideChunk=1024 -Xloggc:./logs/gc-myapp.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintPromotionFailure -XX:+PrintGCApplicationStoppedTime -XX:-UseBiasedLocking -XX:AutoBoxCacheMax=20000 -Djava.security.egd=file:/dev/./urandom -XX:+PrintCommandLineFlags -XX:-OmitStackTraceInFastThrow -XX:ErrorFile=./logs/hs_err_%p.log -Djava.rmi.server.hostname=127.0.0.1 -Dcom.sun.management.jmxremote.port=7001 -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Djava.net.preferIPv4Stack=true -Dfile.encoding=UTF-8"

#ENV JAVA_DEBUG_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"
#ENV JAVA_GC_LOG_OPTS="-Xlog:gc*:file=/tmp/logs/gc.log:time,tid,tags:filecount=10:filesize=10"

# 8C 16G
#ENV JAVA_MEM_OPTS="-server -Xmx12g -Xms12g -XX:MaxMetaspaceSize=256m -XX:MetaspaceSize=256m -XX:MaxDirectMemorySize=2048m -XX:ParallelGCThreads=8 -XX:CICompilerCount=3 -XX:+UseZGC -XX:ZAllocationSpikeTolerance=3 -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/logs -XX:ErrorFile=/tmp/logs/hs_error_pid%p.log"

#2C 4G
#ENV JAVA_MEM_OPTS="-server -Xmx2g -Xms2g -XX:MaxMetaspaceSize=256m -XX:MetaspaceSize=256m -XX:MaxDirectMemorySize=2048m -XX:ParallelGCThreads=2 -XX:CICompilerCount=2 -XX:+UseZGC -XX:ZAllocationSpikeTolerance=2 -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/logs -XX:ErrorFile=/tmp/logs/hs_error_pid%p.log"



ENV SW_AGENT_NAME=""
ENV SW_AGENT_COLLECTOR_BACKEND_SERVICES="localhost:11800"
ENV NACOS_SERVER_ADDR="localhost:8848"
ENV NACOS_NAMESPACE=""
ENV AGENT_OPTS="-Dskywalking.agent.service_name=$SW_AGENT_NAME \
    -Dskywalking.collector.backend_service=$SW_AGENT_COLLECTOR_BACKEND_SERVICES \
    -DNACOS_SERVER_ADDR=$NACOS_SERVER_ADDR \
    -DNACOS_NAMESPACE=$NACOS_NAMESPACE"

# 启动应用程序
ENTRYPOINT ["sh","-c","java $JAVA_OPTS \
    -javaagent:/skywalking/agent/skywalking-agent.jar \
    $AGENT_OPTS \
    -jar app.jar"]

#-javaagent:/skywalking/agent/skywalking-agent.jar -DSW_AGENT_NAME=mall_auth -DSW_AGENT_COLLECTOR_BACKEND_SERVICES=localhost:11800
