FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the JAR built by Jenkins (from target/)
COPY target/calculator-1.0-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]
