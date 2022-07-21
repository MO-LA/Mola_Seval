FROM java:11

VOLUME /tmp

EXPOSE 8081

ARG JAR_FILE=./build/libs/*.jar

ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]