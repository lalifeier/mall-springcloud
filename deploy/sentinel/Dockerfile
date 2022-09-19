FROM openjdk:8-jdk-alpine

ENV SENTINEL_HOME /opt/sentinel-dashboard

RUN mkdir -p ${SENTINEL_HOME}

ADD https://github.com/alibaba/Sentinel/releases/download/1.8.5/sentinel-dashboard-1.8.5.jar ${SENTINEL_HOME}/sentinel-dashboard.jar

WORKDIR ${SENTINEL_HOME}

COPY entrypoint.sh /entrypoint.sh

RUN chmod +x /entrypoint.sh

CMD ["/entrypoint.sh"]
