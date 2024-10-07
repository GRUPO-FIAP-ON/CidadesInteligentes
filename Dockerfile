# Dockerfile

# Use uma imagem base do OpenJDK
FROM openjdk:17-jdk-alpine

# Defina o diretório de trabalho
WORKDIR /app

# Copie o script gradlew e o diretório gradle
COPY gradlew gradlew
COPY gradle gradle

# Adicione permissões de execução ao gradlew
RUN chmod +x gradlew

# Copie o arquivo build.gradle e as dependências necessárias
COPY build.gradle build.gradle
COPY settings.gradle settings.gradle

# Copie o código fonte
COPY src ./src

# Execute o Gradle para compilar a aplicação
RUN ./gradlew build -x test

# Exponha a porta da aplicação
EXPOSE 8080

# Comando para executar a aplicação
CMD ["java", "-jar", "build/libs/CidadesInteligentes-0.0.1-SNAPSHOT.jar"]
