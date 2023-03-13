FROM openjdk:17-jdk-alpine
CMD .\gradlew clean build
MAINTAINER doszke.pl
COPY build/libs/guitar-store-0.0.1-SNAPSHOT.jar guitar-store.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "guitar-store.jar"]