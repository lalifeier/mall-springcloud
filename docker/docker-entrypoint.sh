#!/bin/sh

JAVA_VERSION=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}' | awk -F '.' '{print $1}')

# 2C4G
JAVA_HEAP_SIZE=${JAVA_HEAP_SIZE:-2g}
JAVA_DEBUG_PORT=${JAVA_DEBUG_PORT:-5005}
JAVA_JMX_HOST=${JAVA_JMX_PORT:-$(ip route show default | awk '/default/ {print $3}' | head -n 1)}
JAVA_JMX_PORT=${JAVA_JMX_PORT:-9010}
PARALLEL=$(nproc)

# 设置 Java 内存选项
JAVA_MEM_OPTS="-Xmx$JAVA_HEAP_SIZE -Xms$JAVA_HEAP_SIZE -XX:MaxMetaspaceSize=256m -XX:MetaspaceSize=256m -XX:MaxDirectMemorySize=2048m"

# 设置 Java 垃圾回收选项
JAVA_GC_OPTS="-XX:ParallelGCThreads=2 -XX:ConcGCThreads=1"

if [ "$JAVA_VERSION" -ge 11 ]; then
    # Java 11 及以上版本使用 ZGC（Z Garbage Collector）垃圾回收
    JAVA_GC_OPTS="$JAVA_GC_OPTS -XX:+UseZGC -XX:ZAllocationSpikeTolerance=2"
elif [ "$JAVA_VERSION" -ge 7 ]; then
    # Java 7 及以上版本使用 G1（Garbage-First）垃圾回收器
    JAVA_GC_OPTS="$JAVA_GC_OPTS -XX:+UseG1GC -XX:G1HeapRegionSize=8M"
elif [ "$JAVA_VERSION" -ge 6 ]; then
    # Java 6 及以上版本使用 CMS（Concurrent Mark Sweep）垃圾回收器
    JAVA_GC_OPTS="$JAVA_GC_OPTS -XX:+UseConcMarkSweepGC -XX:CMSInitiatingOccupancyFraction=70 -XX:+UseCMSInitiatingOccupancyOnly"
else
    # 使用默认的垃圾回收器配置
    JAVA_GC_OPTS="$JAVA_GC_OPTS -XX:+UseParallelGC"
fi

if [ "$JAVA_VERSION" -ge 9 ]; then
    JAVA_GC_OPTS="$JAVA_GC_OPTS -Xlog:gc*:file=/tmp/logs/gc.log:time,tid,tags:filecount=10,filesize=10m"
else
    JAVA_GC_OPTS="$JAVA_GC_OPTS -Xloggc:/tmp/logs/gc.log -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps"
fi

# 设置 Java 的远程调试选项
JAVA_DEBUG_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:$JAVA_DEBUG_PORT"

# 设置 Java JMX（Java Management Extensions）选项
JAVA_JMX_OPTS="-Dcom.sun.management.jmxremote -Djava.rmi.server.hostname=$JAVA_JMX_HOST -Dcom.sun.management.jmxremote.port=$JAVA_JMX_PORT -Dcom.sun.management.jmxremote.rmi.port=$JAVA_JMX_PORT -Dcom.sun.management.jmxremote.local.only=false -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false"

# 设置 Java 内存溢出选项
JAVA_OOM_OPTS="-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/logs -XX:ErrorFile=/tmp/logs/hs_error_pid%p.log"

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

# java ${JAVA_OPTS:-$JAVA_MEM_OPTS $JAVA_GC_OPTS $JAVA_DEBUG_OPTS $JAVA_JMX_OPTS $JAVA_OOM_OPTS $JAVA_OPTIMIZE_OPTS $JAVA_OTHER_OPTS} $JAVA_AGENT_OPTS -jar app.jar $SPRING_OPTS
java ${JAVA_OPTS:-$JAVA_MEM_OPTS $JAVA_GC_OPTS $JAVA_DEBUG_OPTS $JAVA_JMX_OPTS $JAVA_OOM_OPTS $JAVA_OPTIMIZE_OPTS $JAVA_OTHER_OPTS} $JAVA_AGENT_OPTS org.springframework.boot.loader.JarLauncher $SPRING_OPTS
