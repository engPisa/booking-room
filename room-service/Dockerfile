# ======================================================================
# ESTÁGIO 1: BUILD - O "CONSTRUTOR"
# ======================================================================
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn package -DskipTests

# ======================================================================
# ESTÁGIO 2: RUNTIME - A "VERSÃO FINAL"
# ======================================================================
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8081

# Esta linha é IDÊNTICA
ENTRYPOINT ["java", "-jar", "app.jar"]