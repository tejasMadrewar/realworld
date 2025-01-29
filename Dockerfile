FROM openjdk:17-alpine AS backend

WORKDIR /app

COPY target/*.jar /app/app.jar
COPY wait-for-it.sh /wait-for-it.sh

RUN chmod +x /wait-for-it.sh

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]