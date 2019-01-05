# Start with a base image containing Java runtime
FROM maven:3.6-jdk-10-slim

ADD . .
# Add Maintainer Info
LABEL maintainer="elhacen.dia@outlook.fr"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080
#RUN maven to build the jar
RUN mvn clean install
# The application's jar file
ARG JAR_FILE=target/acodes-mauritel-api-*.jar

# Add the application's jar to the container
ADD ${JAR_FILE} acodes-mauritel-api.jar
#debug purpose 
RUN ls 
# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","acodes-mauritel-api.jar"]