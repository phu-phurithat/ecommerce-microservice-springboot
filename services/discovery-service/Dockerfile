# Stage 1: Build the application using Maven
FROM maven:3.8.4-openjdk-17-slim as builder

# Set the working directory
WORKDIR /app

# Copy the pom.xml and the source code to the container
COPY pom.xml .
COPY src ./src

# Build the application (skip tests in this case)
RUN mvn clean package -DskipTests

# Stage 2: Run the application using a JRE
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the jar file from the build stage to the container
COPY --from=builder /app/target/discovery-service-0.0.1-SNAPSHOT.jar /app/discovery-service.jar

# Set the command to run the application
ENTRYPOINT ["java", "-jar", "/app/discovery-service.jar"]
