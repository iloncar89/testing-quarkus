FROM openjdk:19
ARG JAR_FILE=./target/testing-quarkus-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} /app.jar
ENTRYPOINT ["java", "-cp", "-jar", ""]