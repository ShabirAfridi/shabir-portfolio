FROM ubuntu:latest
LABEL authors="pc"

ENTRYPOINT ["top", "-b"]
# ── Stage 1: Build the app with Maven ──────────────────────────
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests

# ── Stage 2: Run the built jar on a lightweight JRE ────────────
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/portfolio-app.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]