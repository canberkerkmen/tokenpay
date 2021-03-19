create fatJar

./gradlew clean bootJar

create docker image

docker build -t tokenpay-service:1.0 .
