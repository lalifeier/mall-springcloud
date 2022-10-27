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