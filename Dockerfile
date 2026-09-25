#docker file permet de mettre pom , src dans le serveur et le *jar permet de pas se casser la tet avec le nom tu le copie au memem endroit jste tu met app.jar

FROM maven:3.9.16-eclipse-temurin-26 AS build
WORKDIR /app-build
COPY pom.xml .
COPY src/ ./src
RUN mvn clean package -DskipTests
RUN cp target/*.jar application.jar

FROM eclipse-temurin:26-jdk AS lancement
WORKDIR /app-lancement
COPY --from=build /app-build/application.jar application.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "application.jar"]