FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/pederapido-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} pederapido-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Dserver.port=8080 -Djava.security.egd=file:/dev/./urandom -jar /hepederapido-0.0.1-SNAPSHOT.jar"]