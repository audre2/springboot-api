# Etapa 1: Build da aplicação com Maven (executa testes)
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Etapa 2: Imagem final com JRE leve
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/springboot-api-*.jar app.jar

# Variáveis de ambiente e porta
ENV JAVA_OPTS=""
EXPOSE 8080

# Comando de execução
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]