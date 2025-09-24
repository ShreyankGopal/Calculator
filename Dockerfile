# Use an official OpenJDK 8 image as base
FROM openjdk:8-jdk-alpine

# Set working directory inside container
WORKDIR /app

# Copy Maven project files
COPY pom.xml .
COPY src ./src

# Install Maven inside container
RUN apk add --no-cache maven

# Build the project (produces target/calculator-1.0-SNAPSHOT.jar)
RUN mvn clean package -DskipTests

# Expose port (if your app had a server; optional here)
# EXPOSE 8080

# Default command to run Calculator class
CMD ["java", "-cp", "target/calculator-1.0-SNAPSHOT.jar", "com.example.Calculator"]
