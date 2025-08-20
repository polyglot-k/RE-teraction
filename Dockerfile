# ===============================
# 1. Build Stage
# ===============================
FROM gradle:8.14.3-jdk21 AS build
WORKDIR /app

# 존재하는 파일만 COPY
COPY build.gradle settings.gradle ./

# gradle wrapper 디렉토리가 있다면 추가
# COPY gradle ./gradle

# 의존성 캐시
RUN gradle dependencies --no-daemon

# 소스 복사
COPY src ./src

# 빌드
RUN gradle bootJar --no-daemon -x test

# ===============================
# 2. Run Stage
# ===============================
FROM amazoncorretto:21
WORKDIR /app

COPY --from=build /app/build/libs/*SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-Duser.timezone=GMT+9", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]
