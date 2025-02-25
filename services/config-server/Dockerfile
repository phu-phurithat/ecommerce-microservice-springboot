FROM openjdk:23-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Create user
RUN addgroup --system spring && adduser --system spring --ingroup spring

# Change to user
USER spring:spring

COPY target/*.jar app.jar

EXPOSE 8888

CMD ["java", "-jar", "app.jar"]