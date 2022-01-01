FROM openjdk:17-alpine
ARG JAR_FILE
ARG JAVA_OPTS
RUN mkdir -p /app/logs
WORKDIR /app
ADD target/${JAR_FILE} /app/lib/service.jar
EXPOSE 8080
CMD /usr/bin/java ${JAVA_OPTS} -jar /app/lib/service.jar