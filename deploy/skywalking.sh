#!/bin/sh

#skywalking
#https://skywalking.apache.org/downloads
wget https://dlcdn.apache.org/skywalking/java-agent/8.12.0/apache-skywalking-java-agent-8.12.0.tgz
tar -zxvf apache-skywalking-java-agent-8.12.0.tgz
cp skywalking-agent/optional-plugins/apm-spring-webflux-5.x-plugin-8.12.0.jar skywalking-agent/plugins/
#cp skywalking-agent/optional-plugins/apm-spring-cloud-gateway-2.1.x-plugin-8.12.0.jar skywalking-agent/plugins/
cp skywalking-agent/optional-plugins/apm-spring-cloud-gateway-3.x-plugin-8.12.0.jar skywalking-agent/plugins/

#VM options
-javaagent:/opt/apache-skywalking-java-agent/skywalking-agent.jar
-Dskywalking.agent.service_name=APP_NAME
-Dskywalking.collector.backend_service=127.0.0.1:11800
-Dskywalking.plugin.toolkit.log.grpc.reporter.server_host=127.0.0.1,
-Dskywalking.plugin.toolkit.log.grpc.reporter.server_port=11800 -jar xx.jar

#java -javaagent:/opt/apache-skywalking-java-agent/skywalking-agent.jar=agent.service_name=自定义服务名,collector.backend_service=127.0.0.1:11800,plugin.toolkit.log.grpc.reporter.server_host=127.0.0.1,plugin.toolkit.log.grpc.reporter.server_port=11800 -jar xx.jar
