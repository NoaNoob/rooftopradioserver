FROM tomcat:8.0.20-jre8

RUN mkdir /usr/local/tomcat/webapps/myapp

COPY /target/radio-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/radio.war