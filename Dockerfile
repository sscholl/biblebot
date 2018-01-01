FROM openjdk:8-jdk-alpine
VOLUME /tmp
#ARG JAR_FILE
#ADD ${JAR_FILE} /app.jar
ADD target/biblebot-0.0.1-SNAPSHOT.jar /app.jar
RUN mkdir /app && unzip -q /app.jar -d /app

ENV BIBLEBOT_INTEGRATION_HOST ""
ENV BIBLEBOT_INTEGRATION_PORT 8080
ENV BIBLEBOT_API_HOST ""
ENV BIBLEBOT_API_PORT ""

#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
ENTRYPOINT java -cp /app org.springframework.boot.loader.JarLauncher --server.port=${BIBLEBOT_INTEGRATION_PORT} -Djava.security.egd=file:/dev/./urandom