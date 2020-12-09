FROM openjdk:8-jdk-alpine

RUN addgroup -S challenge && adduser -S challenge -G challenge
USER challenge:challenge

ARG CHALLENGE_JAR=./target/gamers-directory-0.0.1-SNAPSHOT.jar
ADD ${CHALLENGE_JAR} challenge.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=production","-jar","challenge.jar"]