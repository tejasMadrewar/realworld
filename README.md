# Realworld blog rest API


## How it works
It uses Spring boot 3 (Java 17)
* Database - H2 or Mysql
* Security - Spring security JWT


## Build
```
mvnw clean package
```
Set environment variable specified in file .env file
If database is not set it will use h2 database as default to save data

## Run

### Run local
```
java -jar target\realworld-0.0.1-SNAPSHOT.jar
```

### Run in docker
It will use mysql container and create the docker image of jar file
```
docker compose up -d
```
Access API at http://localhost:8080
