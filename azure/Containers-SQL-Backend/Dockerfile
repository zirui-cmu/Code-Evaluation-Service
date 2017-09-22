#ing Ubuntu 16.04 as the base image
FROM ubuntu:16.04

# Install latest version of Java
RUN apt-get update -y && \
    apt-get install default-jre -y

ADD target/Containers-SQL-Backend-1.0.0-SNAPSHOT.jar /Containers-SQL-Backend-1.0.0-SNAPSHOT.jar

# Open the following ports
EXPOSE 8080

# Define the command which runs when the container starts
CMD ["java -jar Containers-SQL-Backend-1.0.0-SNAPSHOT.jar"]

# Use bash as the container's entry point
ENTRYPOINT ["/bin/bash", "-c"]
