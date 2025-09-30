FROM openjdk:17

ARG JAR_FILE=/build/libs/ex0930_docker-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} /doc_ex2.jar

ENTRYPOINT [ "java", "-jar", "/doc_ex2.jar" ]