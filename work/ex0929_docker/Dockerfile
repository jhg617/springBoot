FROM openjdk:17-alpine

ARG JAR_FILE=/build/libs/ex0929_docker-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} /docker_boot.jar

ENTRYPOINT [ "java", "-jar", "/docker_boot.jar" ]