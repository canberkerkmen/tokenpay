FROM adoptopenjdk/openjdk11:alpine-slim

WORKDIR /opt/app

ARG JAR_FILE=tokenpay-service-1.0-SNAPSHOT.jar

COPY build/libs/${JAR_FILE} ${JAR_FILE}

CMD java -jar tokenpay-service-1.0-SNAPSHOT.jar