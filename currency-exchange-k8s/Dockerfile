# Stage 1
FROM eclipse-temurin:21-jdk-jammy AS builder

WORKDIR /app

COPY target/*.jar app.jar


# Stage 2
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

COPY --from=builder /app/app.jar app.jar

EXPOSE 9000

ENTRYPOINT ["java", "-jar", "/app/app.jar"]