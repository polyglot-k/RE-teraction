# ===============================
# 1. Build Stage
# ===============================
FROM gradle:8.14.3-jdk21 AS build
WORKDIR /app

COPY build.gradle settings.gradle gradle.properties ./
COPY gradle ./gradle
RUN gradle dependencies --no-daemon

COPY src ./src

RUN gradle bootJar --no-daemon -x test

# ===============================
# 2. Run Stage
# ===============================
FROM amazoncorretto:21
WORKDIR /app

COPY --from=build /app/build/libs/*SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-Duser.timezone=GMT+9", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]
