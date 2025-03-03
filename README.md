# Prueba Técnia - API de Gestión de Equipo de Fútbol.

Este proyecto es una Api Rest desarollada con **Spring Boot 3** y **Java 17** que permite gestionar equipos de fútbol.

# Instalación y Ejecución

### **Ejecutar el Proyecto con Maven (Localmente)**

1. **Clonar el repositorio**
```sh
git clone https://github.com/afkfarias/prueba-tecnica.git
```
```sh
cd prueba-tecnica
```
- Levantar el proyecto:
```sh
mvn spring-boot:run
```

2. **Ejecutar con Docker**

- Tener instalado docker :  
```sh
docker -version
```
- Construir la imagen con :
```sh
docker build -t prueba-tecnica-afarias .
```
- Ejecutar 
```sh 
docker run -p 8080:8080 prueba-tecnica-afarias
```

3. **Ejecutar con docker-compose**

- Ejecutar: 
```sh
docker-compose up --build
```
## Api Documentacion

La documentación de la Api se encuentra disponible en : 

http://localhost:8080/swagger-ui/index.html