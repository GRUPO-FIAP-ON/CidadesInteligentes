FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY gradlew gradlew
COPY gradle gradle

RUN chmod +x gradlew

COPY build.gradle build.gradle
COPY settings.gradle settings.gradle

COPY src ./src

RUN ./gradlew build -x test

EXPOSE 8080

CMD ["java", "-jar", "build/libs/CidadesInteligentes-0.0.1-SNAPSHOT.jar"]
