FROM eclipse-temurin:17-jdk-alpine as builder

WORKDIR /workspace/app

COPY build/libs/*.jar app.jar

RUN java -Djarmode=layertools -jar app.jar extract

FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=builder /workspace/app/dependencies/ ./
COPY --from=builder /workspace/app/spring-boot-loader/ ./
COPY --from=builder /workspace/app/snapshot-dependencies/ ./
COPY --from=builder /workspace/app/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
