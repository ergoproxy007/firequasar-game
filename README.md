# firequasar-game
Operación Fuego de Quasar

## Descripción
Programa que calcula la posición de astronaves a través de triangulación y es capaz de descifrar distintos mensajes de auxilio eviado por las naves.

### Firmas a tener en cuenta según principales requerimientos:
- **Input:** distancia al emisor tal cual se recibe en cada satélite
- **Output:** las coordenadas ‘x’ e ‘y’ del emisor del mensaje func
- func GetLocation(distances ...float32) (x, y float32)
  - Firma encontrada en la siguiente interfaz y su clase concreta:
  - <pre><code>double[] getLocation(double[][] coordinates, double[] distances) method. Definida en interface->TrilaterationService.java e implementada en class->NonLinearTrilaterationService.java</code></pre>
######
- **Input:** el mensaje tal cual es recibido en cada satélite
- **Output:** el mensaje tal cual lo genera el emisor del mensaje
- func GetMessage(messages ...[]string) (msg string)
  - Firma encontrada en la siguiente clase concreta:
  - <pre><code>CompletionStage< String > getMessage(String[]... messages) method. Implementada en class->MessageService.java</code></pre>

## Tabla de contenido
**[Repositorio](#repositorio)**<br>
**[Arquitectura](#arquitectura)**<br>
**[Caracteristicas](#caracteristicas)**<br>
**[Manual de usuario](#manual-de-usuario)**<br>
**[Learn More](#learn-more)**<br>

## Repositorio
url: [https://github.com/ergoproxy007/firequasar-game](https://github.com/ergoproxy007/firequasar-game)
######
git clone https://github.com/ergoproxy007/firequasar-game.git

## Arquitectura
Detalle del diseño y arquitectura definidos en la aplicación de firequasar:

#### Diseño
Se utiliza el enfoque de desarrollo **DDD (Domain-Driven Design)** para cumplir con necesidades complejas mediante una profunda conexión entre la implementación y los conceptos del modelo y núcleo del negocio.
######
**CQRS** son las siglas de Command Query Responsibility Segregation. Es un patrón que esencia busca la separación del un modelo usado para actualizar la información y otro modelo usado para leer la información.
######
Más información de estos conceptos [https://martinfowler.com/tags/domain%20driven%20design.html](https://martinfowler.com/tags/domain%20driven%20design.html)
######
Se busco con mucho empeño en este proyecto que la lógica de dominio y de negocio estuviera separada de implementaciones amarradas a uso de librerias o frameworks, de manera que se pudiera representar que en este aplicativo seria sencillo cambiar o evolucionar las librerias (infrastructure package) si necesidad de modificar la lógica de negocio (domain and query package)
######
Internamente en la aplicación se puede encontrar implementaciones patrones tales como Factory, Builder, Computación Asincrónica a través del uso de CompletionStage y Futuros (promesas).
Implementaciones usando principios SOLID como clases con responsabilidad unica, encapsulamiento, abstracciones con interface, herencia con clases abstractas,
uso de Generics, Jeraquia de Excepciones, AOP, Stream, programación funcional, entre otros.
######
Las pruebas unitarias desarrolladas también cumplen con el mismo estandar de calidad con que se construyo el código funcional, buscando probar todos los caminos y una alta cobertura.
######
Las pruebas estan totalmente desacopladas de dependencias, como conexiones a base de datos o consumo de servicios externos, por lo que puede ser ejecutada con total simpleza y tranquilidad.
######
La aplicación es una RESTful API basada en arquitectura de Micro Servicios, desarrollada con el framework Spring Boot y Spring Core.
######
En este momento cuenta con 4 servicios rest (2 Post y 2 Get) con las siguientes firmas:
######
Para ver mas detalle sobre los Json de entrada y ejemplo puede revisar la sección de **Manual de usuario y Learn More.**

#### Diagrama de Componentes

#### Diagrama de Clases

## Caracteristicas
---
###### Características de la aplicación
 Librerias y/o Dependencias más importantes:

   Dependency Name | Usage         | Version | License      |
   -------------   | ------------- | ------- | -------------
   Java            | Java Library  | 8       | OpenJDK Open Source |
   Spring Boot     | Web and DI Java framework     | 2.5.3  | Apache License 2.0 |
   Spring Data     | Starter for using Spring Data JPA with Hibernate  | 2.5.3 | Apache License 2.0 |
   H2              | in-memory database, embedded  | 1.4    | Eclipse Public License |
   Trilateration   | Solves a formulation of n-D space trilateration problem | 1.0.2 | MIT License |
   JUnit           | Unit Testing Library    | 5.jupiter    | Eclipse Public License |
   Mockito         | Test                    |              | MIT license |
   JaCoCo          | Java code coverage tools | 0.8.7       | GPL v2      |
######
La aplicación dispone de los 4 Servicios Rest con las siguiente firmas:
#### Path
- #### /api
<pre><code>POST /topsecret </code></pre>
<pre><code>POST /topsecret_split </code></pre>
<pre><code>GET /topsecret_split/{satellite_name} </code></pre>
<pre><code>GET /spaceships </code></pre>
######
Para más detalle del consumo de los servicios, ir a la secciones de Manual de usuario y Learn More.

## Manual de usuario
###### Como ejecutar este programa
Puede ejecutar el programa en ambiente local una vez descargado el codigo fuente en [firequasar-game.git](https://github.com/ergoproxy007/firequasar-game.git):

En ambiente local, tienen dos opciones para ejecutar y/o probar el programa:
- 1.- Correr el proyecto directamente desde IntelliJ o Elipse usando Run Configurations
- 2.- O también puede correr el proyecto con el siguiente comando en el subproyecto /firequasar: **mvn spring-boot:run**

### 1. A través de los archivos de Jmeter (recomendado)
La creación de los archivos en Jmeter tiene como objetivo simplificar el trabajo de ejecución de los servicios rest,
pensando a futuro poder agregar assets de validación o baterias de pruebas de rendimiento, entre otras cosas, que te brinda un programa como Jmeter.
[Screenshot]

1.1 Descarga Jmeter [aquí](https://curl.se/windows/)
1.2 Abrir el archivo en la ruta firequasar-game/firequasar/resources/performance
1.3 Ejecutar los servicios como disponga (en View Results Tree podrá ver los resultados):
[Screenshot](https://github.com/ergoproxy007//firequasar-game/tree/main/firequasar/resources/user-manual/curl-example.PNG?raw=true)

### 2. Usando CURL
2.1. Descarga curl [aquí](https://curl.se/windows/)
2.2. Descargar git bash [aquí](https://gitforwindows.org/)
2.3. Estos son 2 ejemplos para la ejecución de algunos de los servicios:
2.3.1.  curl topsecret_split POST service:
<pre><code>curl -X POST "http://localhost:8081/api/topsecret_split/Sato" -H "accept: application/json" -H  "Content-Type: application/json" -d '{"distance": 142.7,"message": ["este", "", "un", "", "mensaje"]}'</code></pre>
2.3.2.  curl topsecret_split GET service:
<pre><code>curl -X GET "http://localhost:8081/api/topsecret_split/Sato" -H  "accept: application/json" -H "Content-Type: application/json"</code></pre>
2.3.3.  Respuesta ejemplo:
[Screenshot][Screenshot](https://github.com/ergoproxy007//firequasar-game/tree/main/firequasar/resources/user-manual/jmeter-example.PNG?raw=true)

## Learn More

#### Test Coverage
Informe de Test Coverage ejecutado por JaCoCo el 25/08/2021
Command: mvn clean test
######
Path: firequasar-game/firequasar/target/site/jacoco/index.html
[Screenshot]
