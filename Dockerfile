FROM eclipse-temurin:17-jdk-focal AS builder
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

FROM openjdk:17-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/app.jar

CMD ["java", "-jar", "/app/app.jar"]