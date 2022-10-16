FROM openjdk:8-jdk-alpine
LABEL maintainer=lalifeier@gmail.com

ENV TIME_ZONE=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TIME_ZONE /etc/localtime && echo $TIME_ZONE > /etc/timezone

ADD "https://dlcdn.apache.org/skywalking/java-agent/8.12.0/apache-skywalking-java-agent-8.12.0.tgz" .
RUN tar -xvf apache-skywalking-java-agent-8.12.0.tgz  -C /opt && \
    rm -rf apache-skywalking-java-agent-8.12.0.tgz

ARG JAR_FILE="target/*.jar"
COPY ${JAR_FILE} /app.jar

ENV JAVA_OPTS=""
ENV SKYWALKING_AGENT_SERVICE_NAME=""
ENV SKYWALKING_COLLECTOR_BACKEND_SERVICE=""
ENV AGENT_OPTS="-Dskywalking.agent.service_name=$SKYWALKING_AGENT_SERVICE_NAME -Dskywalking.collector.backend_service=$SKYWALKING_COLLECTOR_BACKEND_SERVICE"

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -javaagent:/opt/skywalking-agent/skywalking-agent.jar $AGENT_OPTS -jar /app.jar"]