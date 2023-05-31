# syntax=docker/dockerfile:experimental

FROM openjdk:17-alpine
COPY /build/libs/authorization-server-0.0.1-SNAPSHOT.jar /usr/local/lib/app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/usr/local/lib/app.jar"]