FROM openjdk:11

MAINTAINER petuch03
COPY ./target/simple-java-bot-1.0.jar /test-docker/
CMD ["java", "-jar", "/test-docker/simple-java-bot-1.0.jar"]