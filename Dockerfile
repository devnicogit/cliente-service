
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/cliente-service-0.0.1-SNAPSHOT.jar /app/cliente-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "cliente-service.jar"]
