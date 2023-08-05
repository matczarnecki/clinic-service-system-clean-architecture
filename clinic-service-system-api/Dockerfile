FROM openjdk:17-jdk AS builder
WORKDIR /app
COPY .mvn .mvn
COPY mvnw pom.xml checkstyle.xml ./
RUN ./mvnw dependency:resolve
COPY src ./src
RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk
COPY --from=builder app/target/*.jar app/app.jar
WORKDIR /app
CMD ["java", "-jar", "app.jar"]

