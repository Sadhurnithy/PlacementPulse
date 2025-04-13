FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . /app

RUN ./mvnw clean install -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "out/artifacts/PlacementPulse_jar/PlacementPulse.jar"]
