FROM maven:3.6.1-jdk-8-slim
# FROM maven:3.6.1-jdk-11-slim

WORKDIR /app
RUN curl https://start.spring.io/starter.zip -d dependencies=web -d bootVersion=2.1.5.RELEASE -d applicationName=Prime -d baseDir=prime -o prime.zip
RUN unzip prime.zip
RUN rm prime.zip

RUN rm prime/src/main/java/com/example/demo/Prime.java
# RUN rm prime/src/main/resources/application.properties
# RUN cp 'logging.file=/logs/app.log' > prime/src/main/resources/application.properties

COPY ExecutionTimeInterceptor.java prime/src/main/java/com/example/demo/
COPY Prime.java prime/src/main/java/com/example/demo/

WORKDIR /app/prime
RUN mvn package
# WORKDIR target
# RUN ls
# COPY target/demo-0.0.1-SNAPSHOT.jar app.jar
RUN cp target/demo-0.0.1-SNAPSHOT.jar /app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
