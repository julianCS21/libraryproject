# Usa una imagen de Maven como base para compilar el proyecto
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY . .
RUN mvn package -DskipTests

# Usa una imagen de OpenJDK en Alpine Linux como base para ejecutar la aplicación
FROM openjdk:17-alpine
WORKDIR /app

# Copia el JAR construido anteriormente al contenedor
COPY --from=build /app/target/library-0.0.1-SNAPSHOT.jar /app/library.jar

# Expone el puerto en el que se ejecutará la aplicación Spring Boot
EXPOSE 8080

# Comando para ejecutar la aplicación cuando se inicie el contenedor
CMD ["java", "-jar", "library.jar"]

