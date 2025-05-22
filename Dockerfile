FROM openjdk:17-alpine
WORKDIR /app
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar", "-Duser.timezone=Asia/Seoul", "/app/app.jar"]