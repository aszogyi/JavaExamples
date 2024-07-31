# Step 1: Build the application
FROM ghcr.io/graalvm/graalvm-ce:21.3.0-java17 AS build
WORKDIR /app

# Install Gradle
RUN gu install native-image

# Copy Gradle build files and source code
COPY build.gradle.kts settings.gradle.kts ./
COPY gradle gradle
COPY src ./src

# Build the application and create the native image (if needed)
RUN ./gradlew build -x test

# Step 2: Create the Docker image for the JAR
FROM ghcr.io/graalvm/graalvm-ce:21.3.0-java17
WORKDIR /app

# Copy the built JAR file
COPY --from=build /app/build/libs/*.jar app.jar

# Set the entry point for the application
ENTRYPOINT ["java", "-jar", "app.jar"]