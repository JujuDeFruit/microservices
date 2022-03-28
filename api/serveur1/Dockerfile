# syntax=docker/dockerfile:1

FROM openjdk:17-alpine
ENV HOME=/app

WORKDIR $HOME

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src

EXPOSE 8080

CMD ["./mvnw", "spring-boot:run"]
