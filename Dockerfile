FROM openjdk:17
EXPOSE 80 9010
ARG JAR_FILE=/build/libs/*.jar
ARG JMX_PORT=9010
ARG HOST_NAME=localhost
COPY ${JAR_FILE} app.jar
ENTRYPOINT {"java","-Dcom.sun.management.jmxremote=true","-Dcom.sun.management.jmxremote.local.only=false",
"-Dcom.sun.management.jmxremote.port=${JMX_PORT}","-Dcom.sun.management.jmxremote.ssl=false",
"-Dcom.sun.management.jmxremote.authenticate=false","-Djava.rmi.server.hostname=${HOSTNAME}",
"-Dcom.sun.management.jmxremote.rmi.port=${JMX_PORT}","-jar","-Duser.timezone=Asia/Seoul","-Dspring.profiles.active=release","/app.jar"}