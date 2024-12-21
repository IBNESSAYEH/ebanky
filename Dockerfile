FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/E-Bankify-Security-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
