FROM openjdk:8-jre-alpine

ADD target/radio-*.jar /app/app.jar

ENTRYPOINT [ "java", "-jar", "/app/app.jar" ]