FROM openjdk:17-jdk-alpine
COPY target/springboot-docker-0.0.1.jar springboot-docker.jar
ENTRYPOINT ["java","-jar","/springboot-docker.jar"]