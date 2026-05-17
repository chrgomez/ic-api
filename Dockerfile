# Usamos una imagen base de Java 17 súper ligera
FROM eclipse-temurin:17-jdk-alpine

# Definimos el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos el JAR generado por el pipeline al contenedor
COPY target/*.jar app.jar

# Exponemos el puerto donde corre Spring Boot
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
