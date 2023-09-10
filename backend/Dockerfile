# Stage 1: Build the application
FROM maven:3.6-openjdk-17 AS build-env
WORKDIR /app

# Copy project and build layers
COPY src ./src
COPY pom.xml ./
RUN mvn install -DskipTests

# Stage 2: Create the application image
FROM amazoncorretto:17
WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=build-env /app/target/*.jar ./app.jar

# Define the entry point for the container
#CMD ["java", "-jar", "./app.jar"]
CMD ["java","jar", "./", "-app.jar"]