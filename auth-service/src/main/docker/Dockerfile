FROM openjdk:8-jdk-alpine
LABEL maintainer=lalifeier@gmail.com

ENV TIME_ZONE=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TIME_ZONE /etc/localtime && echo $TIME_ZONE > /etc/timezone

#ADD "https://dlcdn.apache.org/skywalking/java-agent/8.12.0/apache-skywalking-java-agent-8.12.0.tgz" .
#RUN tar -zxvf apache-skywalking-java-agent-8.12.0.tgz  && \
#    cp skywalking-agent/optional-plugins/apm-spring-webflux-5.x-plugin-8.12.0.jar skywalking-agent/plugins/  && \
#    cp skywalking-agent/optional-plugins/apm-spring-cloud-gateway-2.1.x-plugin-8.12.0.jar skywalking-agent/plugins/  && \
#    cp skywalking-agent/optional-plugins/apm-spring-cloud-gateway-3.x-plugin-8.12.0.jar skywalking-agent/plugins/  && \
#    mv skywalking-agent /opt/skywalking-agent  && \
#    rm -rf apache-skywalking-java-agent-8.12.0.tgz

ARG JAR_FILE="target/*.jar"
#COPY ${JAR_FILE} /app.jar

#ARG finalName
#ADD $finalName /app.jar

ENV JAVA_OPTS=""
ENV SKYWALKING_AGENT_SERVICE_NAME=""
ENV SKYWALKING_COLLECTOR_BACKEND_SERVICE="127.0.0.1:11800"
ENV AGENT_OPTS="-Dskywalking.agent.service_name=$SKYWALKING_AGENT_SERVICE_NAME -Dskywalking.collector.backend_service=$SKYWALKING_COLLECTOR_BACKEND_SERVICE"

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -javaagent:/opt/skywalking-agent/skywalking-agent.jar $AGENT_OPTS -jar /app.jar"]


#-javaagent:/opt/skywalking-agent/skywalking-agent.jar -DSW_AGENT_NAME=mall_auth -DSW_AGENT_COLLECTOR_BACKEND_SERVICES=127.0.0.1:11800