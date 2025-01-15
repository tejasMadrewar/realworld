FROM openjdk:17-alpine AS backend
ADD target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]