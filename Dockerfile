FROM eclipse-temurin:17-jre-alpine AS base
WORKDIR /workspace/app

COPY agent /usr/local/agent
COPY docker/docker-entrypoint.sh /usr/local/bin/docker-entrypoint.sh
RUN chmod +x /usr/local/bin/docker-entrypoint.sh

ENTRYPOINT ["/usr/local/bin/docker-entrypoint.sh"]
CMD ["/bin/sh"]

#FROM eclipse-temurin:17-jdk AS deps
FROM gradle:8.1.1-jdk17-focal AS builder
WORKDIR /workspace/app

COPY . .

RUN --mount=type=cache,id=gradle,target=~/.gradle gradle dependencies --no-daemon
RUN gradle clean build -x test --no-daemon

FROM builder AS demo-service-builder
WORKDIR /workspace/app

COPY --from=builder mall-cloud-business/mall-cloud-demo/mall-cloud-demo-service/build/libs/*.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM base AS demo-service
WORKDIR /app

COPY --from=demo-service-builder /workspace/app/dependencies/ ./
COPY --from=demo-service-builder /workspace/app/spring-boot-loader/ ./
COPY --from=demo-service-builder /workspace/app/snapshot-dependencies/ ./
COPY --from=demo-service-builder /workspace/app/application/ ./


