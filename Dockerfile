FROM amazoncorretto:17.0.7-alpine
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 8080