FROM eclipse-temurin:23-jre-ubi9-minimal

WORKDIR /app
COPY target/*-shaded.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]