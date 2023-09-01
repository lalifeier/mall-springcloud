FROM eclipse-temurin:17-jre-alpine AS base
WORKDIR /workspace/app

COPY ./agent /usr/local/agent
COPY docker/docker-entrypoint.sh /usr/local/bin/docker-entrypoint.sh
RUN chmod +x /usr/local/bin/docker-entrypoint.sh

ENTRYPOINT ["/usr/local/bin/docker-entrypoint.sh"]
CMD ["/bin/sh"]

# FROM eclipse-temurin:17-jdk AS builder
FROM gradle:8.1.1-jdk17-focal AS builder
WORKDIR /workspace/app

# COPY gradle/ ./gradle/
# COPY buildSrc/ ./buildSrc/
# COPY *.gradle.kts gradle.properties gradlew ./
# COPY **/build.gradle ./

# COPY mall-cloud-business/mall-cloud-admin/build.gradle.kts ./mall-cloud-business/mall-cloud-admin/build.gradle


# RUN --mount=type=cache,id=gradle-cache,target=~/.gradle,sharing=locked gradle dependencies --no-daemon --info --stacktrace

COPY . .

# RUN gradle clean build -x test --no-daemon --info
RUN --mount=type=cache,id=gradle-cache,target=~/.gradle,sharing=locked gradle clean build -x test --no-daemon --info

FROM builder AS demo-service-layers-builder
WORKDIR /workspace/app

COPY --from=builder /workspace/app/mall-cloud-business/mall-cloud-demo/mall-cloud-demo-service/build/libs/*.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM base AS demo-service
WORKDIR /workspace/app

COPY --from=demo-service-layers-builder /workspace/app/dependencies/ ./
COPY --from=demo-service-layers-builder /workspace/app/spring-boot-loader/ ./
COPY --from=demo-service-layers-builder /workspace/app/snapshot-dependencies/ ./
COPY --from=demo-service-layers-builder /workspace/app/application/ ./
