FROM openjdk:17-jdk-slim

#information around who maintains the image
MAINTAINER vinnie.com

COPY build/libs/Accounts-Application-0.0.1-SNAPSHOT.jar Accounts-Application-0.0.1-SNAPSHOT.jar
#copy from local machine to the docker image


#execute the application
ENTRYPOINT["java","-jar","Accounts-Application-0.0.1-SNAPSHOT.jar"]


