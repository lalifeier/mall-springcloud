#!/bin/sh
set -e

java -Dserver.port=8080 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=sentinel-dashboard -jar $SENTINEL_HOME/sentinel-dashboard.jar