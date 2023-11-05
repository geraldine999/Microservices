#FROM maven:3-jdk-8-alpine

#WORKDIR /usr/src/app

#COPY . /usr/src/app
#RUN mvn package

#ENV PORT 5000
#EXPOSE $PORT
#CMD [ "sh", "-c", "mvn -Dserver.port=${PORT} spring-boot:run" ]

FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app-jar
ENTRYPOINT ["java","-jar","/app.jar"]

