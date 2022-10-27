#!/bin/sh

#skywalking
#https://skywalking.apache.org/downloads
wget https://dlcdn.apache.org/skywalking/java-agent/8.12.0/apache-skywalking-java-agent-8.12.0.tgz
tar -zxvf apache-skywalking-java-agent-8.12.0.tgz
cp skywalking-agent/optional-plugins/apm-spring-webflux-5.x-plugin-8.12.0.jar skywalking-agent/plugins/
#cp skywalking-agent/optional-plugins/apm-spring-cloud-gateway-2.1.x-plugin-8.12.0.jar skywalking-agent/plugins/
cp skywalking-agent/optional-plugins/apm-spring-cloud-gateway-3.x-plugin-8.12.0.jar skywalking-agent/plugins/
mv skywalking-agent /opt/skywalking-agent
rm -rf apache-skywalking-java-agent-8.12.0.tgz

#VM options
#-javaagent:/opt/skywalking-agent/skywalking-agent.jar
#-Dskywalking.agent.service_name=APP_NAME
#-Dskywalking.collector.backend_service=127.0.0.1:11800
#-Dskywalking.plugin.toolkit.log.grpc.reporter.server_host=127.0.0.1,
#-Dskywalking.plugin.toolkit.log.grpc.reporter.server_port=11800 -jar xx.jar

#java -javaagent:/opt/apache-skywalking-java-agent/skywalking-agent.jar=agent.service_name=自定义服务名,collector.backend_service=127.0.0.1:11800,plugin.toolkit.log.grpc.reporter.server_host=127.0.0.1,plugin.toolkit.log.grpc.reporter.server_port=11800 -jar xx.jar

#-javaagent:/opt/skywalking-agent/skywalking-agent.jar -Dskywalking.agent.service_name=mall_auth -Dskywalking.collector.backend_service=127.0.0.1:11800

#-javaagent:/opt/skywalking-agent/skywalking-agent.jar
#-DSW_AGENT_NAME=mall_auth
#-DSW_AGENT_COLLECTOR_BACKEND_SERVICES=127.0.0.1:11800

#-javaagent:/opt/skywalking-agent/skywalking-agent.jar
## 指定服务名：
#-Dskywalking.agent.service_name=[serverName]
## 指定oap地址：
#-Dskywalking.collector.backend_service=[server-com:11800]
## 指定过滤端点：
#-Dskywalking.trace.ignore_path=/eureka/**,/swagger*/**
## sql执行参数可见
#-Dskywalking.lugin.mysql.trace_sql_parameters=true
