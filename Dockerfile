# Use Eclipse Temurin JDK as the base image for building the Spring Boot application
FROM eclipse-temurin:19-jdk as build

# Set the working directory in the container
WORKDIR /app

# Install Maven
RUN apt-get update && \
    apt-get install -y maven

# Copy the Maven build file(s) into the container
COPY pom.xml /app/

# Copy the source code of the application into the container
COPY src /app/src

# Build the application
RUN mvn package -DskipTests

# Use Eclipse Temurin JRE for running the application in the final stage
FROM eclipse-temurin:19-jre

# Add a non-root user for running the application
RUN addgroup --system spring && adduser --system --group spring
USER spring:spring

# Copy the built application JAR from the build stage to the container
# Note: The path /app/target/*.jar matches the output directory of Maven builds
COPY --from=build /app/target/*.jar /app.jar

# Expose the application's port
EXPOSE 8080/tcp

# Specify the entrypoint to run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]
