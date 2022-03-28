# syntax=docker/dockerfile:1

FROM openjdk:17-alpine
ENV API=/api/serveur1

WORKDIR $API

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]
