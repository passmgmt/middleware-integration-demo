FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /workspace
COPY pom.xml .
COPY src ./src
RUN mvn -q -DskipTests package

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /workspace/target/*.jar app.jar
COPY application.yaml /app/config/application.yaml
EXPOSE 8082
ENTRYPOINT ["java", "-Dspring.config.additional-location=optional:file:/app/config/", "-jar", "app.jar"]