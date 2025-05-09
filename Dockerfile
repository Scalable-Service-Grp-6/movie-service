# ----- Stage 1: Build -----
FROM gradle:8.5-jdk17 AS build
WORKDIR /app

# Copy only Gradle wrapper and build files first to cache dependencies
COPY gradle gradle
COPY gradlew build.gradle settings.gradle ./

# Download dependencies
RUN ./gradlew build -x test --no-daemon || return 0

# Copy the rest of the application code
COPY . .

# Build the application (skip tests for faster build)
RUN ./gradlew bootJar -x test --no-daemon

# ----- Stage 2: Run -----
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy the built jar from the previous stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose application port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
