FROM openjdk:21-jdk AS build
WORKDIR /app
COPY . .
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
