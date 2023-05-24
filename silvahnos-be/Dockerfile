FROM openjdk:17
ARG JAR_FILE=target/SilvaHnos-be.jar
EXPOSE 8090
COPY ${JAR_FILE} SilvaHnos-be.jar
ENTRYPOINT ["java", "-jar", "/SilvaHnos-be.jar"]