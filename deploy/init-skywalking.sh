#!/bin/sh

#https://skywalking.apache.org/downloads
SKYWALKING_AGENT_VERSION="8.16.0"
CURRENT_DIR=$(dirname "$0")
AGENT_DIR=$(cd "$CURRENT_DIR/.." && pwd)/agent

# 检查 `agent` 目录是否存在
if [ ! -d "$AGENT_DIR" ]; then
    # 创建 `agent` 目录
    mkdir -p "$AGENT_DIR"
fi

# 检查 `agent` 目录是否存在文件
if [ -z "$(ls -A "$AGENT_DIR")" ]; then
    wget -O skywalking-agent.tgz "https://dlcdn.apache.org/skywalking/java-agent/${SKYWALKING_AGENT_VERSION}/apache-skywalking-java-agent-${SKYWALKING_AGENT_VERSION}.tgz"
    tar -zxvf skywalking-agent.tgz
    cp skywalking-agent/optional-plugins/apm-spring-webflux-5.x-plugin-${SKYWALKING_AGENT_VERSION}.jar skywalking-agent/plugins/
    cp skywalking-agent/optional-plugins/apm-spring-cloud-gateway-3.x-plugin-${SKYWALKING_AGENT_VERSION}.jar skywalking-agent/plugins/
    mv skywalking-agent "$AGENT_DIR"
#    rm -rf skywalking-agent.tgz
    echo "SkyWalking Agent has been initialized."
else
    echo "SkyWalking Agent already exists. Skipping initialization."
fi


#VM options
#-javaagent:/opt/skywalking-agent/skywalking-agent.jar -Dskywalking.agent.service_name=mall_auth -Dskywalking.collector.backend_service=127.0.0.1:11800
# -Dskywalking.trace.ignore_path=Lettuce/INFO,/actuator/**,/actuator,/swagger*/**,/health/**
#-javaagent:/opt/skywalking-agent/skywalking-agent.jar -Dskywalking.agent.service_name=mall_gateway -Dskywalking.collector.backend_service=127.0.0.1:11800
# -Dskywalking.trace.ignore_path=Lettuce/INFO,/actuator/**,/actuator,/swagger*/**,/health/**

#-javaagent:/opt/skywalking-agent/skywalking-agent.jar
## 指定服务名：
#-Dskywalking.agent.service_name=[serverName]
## 指定oap地址：
#-Dskywalking.collector.backend_service=[server-com:11800]
## 指定过滤端点：
#-Dskywalking.trace.ignore_path=Lettuce/INFO,/actuator/**,/actuator,/swagger*/**,/health/**
## sql执行参数可见
#-Dskywalking.lugin.mysql.trace_sql_parameters=true
