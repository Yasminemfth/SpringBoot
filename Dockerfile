# Étape 1 : compiler avec Maven
FROM maven:3.9.16-eclipse-temurin-26 AS build
WORKDIR /app
COPY pom.xml /app/pom.xml
COPY src/ /app/src
RUN mvn clean package -DskipTests


FROM eclipse-temurin:26-jdk AS lancement
COPY --from=build /app/target/*.jar /app/application.jar
ENTRYPOINT ["java", "-jar", "/app/application.jar"]