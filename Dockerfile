FROM amazoncorretto:17.0.7-alpine
COPY build/libs/*.jar spring_boot_transaction.jar
ENTRYPOINT ["java", "-jar", "spring_boot_transaction.jar"]

EXPOSE 5005
EXPOSE 8080