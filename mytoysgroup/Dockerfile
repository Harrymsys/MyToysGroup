FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} product-0.0.1-SNAPSHOT.jar
COPY ./property/microservice.properties /src/main/resources/microservice.properties 
ENTRYPOINT ["java","-jar","/product-0.0.1-SNAPSHOT.jar", "spring-boot-param", "./src/main/resources/microservice.properties"]
