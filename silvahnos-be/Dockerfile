FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} SilvaHnos-be.jar
ENTRYPOINT ["java", "-jar", "/SilvaHnos-be.jar"]