# syntax=docker/dockerfile:1

FROM openjdk:17-alpine
ENV API=/api

WORKDIR $API

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN sed -i 's/\r$//' mvnw
RUN ./mvnw dependency:go-offline

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]
