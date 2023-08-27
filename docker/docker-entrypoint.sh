#!/bin/sh

JAVA_VERSION=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}')

JAVA_DEBUG_PORT=${JAVA_DEBUG_PORT:-5005}
JAVA_JMX_HOST=${JAVA_JMX_PORT:-0.0.0.0}
JAVA_JMX_PORT=${JAVA_JMX_PORT:-9010}

# 设置 Java 内存选项
JAVA_MEM_OPTS="-Xmx2g -Xms2g -XX:MaxMetaspaceSize=256m -XX:MetaspaceSize=256m -XX:MaxDirectMemorySize=2048m"

# 设置 Java 垃圾回收选项
JAVA_GC_OPTS="-XX:ParallelGCThreads=2 -XX:+UseZGC -XX:ZAllocationSpikeTolerance=2 -Xlog:gc*:file=/tmp/logs/gc.log:time,tid,tags:filecount=10:filesize=10"

# 设置 Java 的远程调试选项
JAVA_DEBUG_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:$JAVA_DEBUG_PORT"

# 设置 Java JMX（Java Management Extensions）选项
JAVA_JMX_OPTS="-Dcom.sun.management.jmxremote -Djava.rmi.server.hostname=$JAVA_JMX_HOST -Dcom.sun.management.jmxremote.port=$JAVA_JMX_PORT -Dcom.sun.management.jmxremote.rmi.port=$JAVA_JMX_PORT -Dcom.sun.management.jmxremote.local.only=false -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false"

# 设置 Java 内存溢出选项
JAVA_OOM_OPTS="-verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/logs -XX:ErrorFile=/tmp/logs/hs_error_pid%p.log"

# 设置 Java 优化选项
JAVA_OPTIMIZE_OPTS="-Djava.security.egd=file:/dev/./urandom -XX:CICompilerCount=2"

# 设置 Java 其他选项
JAVA_OTHER_OPTS="-Duser.timezone=Asia/Shanghai -Dfile.encoding=UTF-8 -Dsun.jnu.encoding=UTF-8"

# 设置应用程序的代理选项
#JAVA_AGENT_OPTS="-javaagent:/usr/local/agent/skywalking-agent.jar
#    -Dskywalking.agent.service_name=${SW_AGENT_NAME:-demo} \
#    -Dskywalking.collector.backend_service=${SW_AGENT_COLLECTOR_BACKEND_SERVICES:-localhost:11800} \
#    -DNACOS_SERVER_ADDR=${NACOS_SERVER_ADDR:-localhost:8848} \
#    -DNACOS_NAMESPACE=$NACOS_NAMESPACE"

# 设置 Spring Boot 应用程序的配置选项
SPRING_OPTS="--spring.profiles.active=${ACTIVE_PROFILE-prod}"

java ${JAVA_OPTS:-"$JAVA_MEM_OPTS $JAVA_GC_OPTS $JAVA_DEBUG_OPTS $JAVA_JMX_OPTS $JAVA_OOM_OPTS $JAVA_OPTIMIZE_OPTS $JAVA_OTHER_OPTS"} JAVA_AGENT_OPTS -jar app.jar $SPRING_OPTS
#java ${JAVA_OPTS:-"$JAVA_MEM_OPTS $JAVA_GC_OPTS $JAVA_DEBUG_OPTS $JAVA_JMX_OPTS $JAVA_OOM_OPTS $JAVA_OPTIMIZE_OPTS $JAVA_OTHER_OPTS"} $AGENT_OPTS org.springframework.boot.loader.JarLauncher $SPRING_OPTS
