FROM openjdk:17-alpine AS backend

WORKDIR /app

ADD target/*.jar app/app.jar
COPY wait-for-it.sh /wait-for-it.sh

RUN chmod +x /wait-for-it.sh

ENTRYPOINT ["java", "-jar", "app.jar"]