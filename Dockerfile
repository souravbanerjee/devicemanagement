FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY target/devices-api.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
