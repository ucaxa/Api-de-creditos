FROM eclipse-temurin:21-jdk-alpine
RUN apk update && apk add curl
WORKDIR /app

# Copia os arquivos do Maven wrapper
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Baixa as dependências (cache separado)
RUN ./mvnw dependency:go-offline

# Copia o código fonte
COPY src ./src

# Constrói o projeto
RUN ./mvnw clean package -DskipTests

# Verifica se o JAR foi criado corretamente
RUN ls -la target/*.jar

ENTRYPOINT ["java", "-jar", "/app/target/creditos-api-0.0.1-SNAPSHOT.jar"]