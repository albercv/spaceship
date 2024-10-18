FROM eclipse-temurin:21-jdk-alpine

# Instalar dependencias necesarias
RUN apk add --no-cache wget tar bash

# Asegurarse de que mvnw tenga permisos de ejecución
WORKDIR /app
COPY . .

RUN chmod +x ./mvnw

# Descargar dependencias
RUN ./mvnw dependency:go-offline -B

# Construir el proyecto
RUN ./mvnw clean package -DskipTests || echo "Maven build failed"

# Exponer el puerto 8080
EXPOSE 8080

# Ejecutar la aplicación
CMD ["java", "-jar", "target/SpaceShip-api-0.0.1-SNAPSHOT.jar"]

