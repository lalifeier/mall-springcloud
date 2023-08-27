FROM eclipse-temurin:17-jre-alpine AS base
WORKDIR /app

COPY agent /usr/local/agent
COPY docker/docker-entrypoint.sh /usr/local/bin/docker-entrypoint.sh
RUN chmod +x /usr/local/bin/docker-entrypoint.sh

#ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS org.springframework.boot.loader.JarLauncher"]
ENTRYPOINT ["/usr/local/bin/docker-entrypoint.sh"]
CMD ["/bin/sh"]

FROM eclipse-temurin:17-jdk AS deps
#FROM gradle:8.1.1-jdk17-focal AS deps

WORKDIR /app

COPY . .

RUN --mount=type=cache,id=gradle,target=~/.gradle ./gradlew dependencies --no-daemon

FROM deps AS build
WORKDIR /app

RUN ./gradlew clean build -x test --no-daemon

FROM base AS demo-service
WORKDIR /app

COPY --from=build mall-cloud-business/mall-cloud-demo/mall-cloud-demo-service/build/target/*.jar app.jar
#RUN java -Djarmode=layertools -jar app.jar extract
#
#FROM base AS demo-service
#WORKDIR /app
#
#COPY --from=demo-service-builder dependencies/ ./
#COPY --from=demo-service-builder spring-boot-loader/ ./
#COPY --from=demo-service-builder snapshot-dependencies/ ./
#COPY --from=demo-service-builder application/ ./


