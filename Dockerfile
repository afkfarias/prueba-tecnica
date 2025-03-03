FROM amazoncorretto:17
WORKDIR /app
COPY target/prueba-tecnica-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]