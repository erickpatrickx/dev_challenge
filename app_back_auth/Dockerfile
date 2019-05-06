FROM openjdk:8-jdk-alpine
FROM maven:alpine

# image layer
WORKDIR /app
ADD pom.xml /app
RUN mvn verify clean --fail-never

# Image layer: with the application
COPY . /app
RUN mvn -v
RUN mvn clean install -DskipTests

ADD /app/target/*.jar target/*.jar app-back-auth-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","app-back-auth-0.0.1-SNAPSHOT.jar"]

