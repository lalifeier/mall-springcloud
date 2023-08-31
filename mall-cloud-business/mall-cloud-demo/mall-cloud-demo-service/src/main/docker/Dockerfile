FROM eclipse-temurin:17-jre-alpine AS layers-builder
WORKDIR /workspace/app

COPY build/libs/*.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM eclipse-temurin:17-jre-alpine
WORKDIR /workspace/app

COPY --from=layers-builder /workspace/app/dependencies/ ./
COPY --from=layers-builder /workspace/app/spring-boot-loader/ ./
COPY --from=layers-builder /workspace/app/snapshot-dependencies/ ./
COPY --from=layers-builder /workspace/app/application/ ./

ENTRYPOINT ["java","org.springframework.boot.loader.JarLauncher"]
