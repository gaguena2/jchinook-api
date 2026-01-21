FROM maven:3.9.9-eclipse-temurin-17 AS builder

WORKDIR /app

# Copia apenas o necessário para aproveitar cache
COPY pom.xml .

# Baixa dependências antes
RUN mvn -B dependency:go-offline

# Copia o restante do projeto
COPY src src

# Build da aplicação
RUN mvn clean package -DskipTests


FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
