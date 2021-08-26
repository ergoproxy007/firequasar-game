# firequasar-game
Operación Fuego de Quasar

## Descripción
Programa que calcula la posición de astronaves a través de triangulación y es capaz de descifrar distintos mensajes de auxilio eviado por las naves

## Tabla de contenido
**[Arquitectura](#arquitectura)**<br>
**[Características de la aplicación](#features)**<br>

## Repositorio
url -> [https://github.com/ergoproxy007/firequasar-game](https://github.com/ergoproxy007/firequasar-game)
######
git clone https://github.com/ergoproxy007/firequasar-game.git

## Arquitectura
Detalle de la arquitectura de firequasar

#### Diagrama de Componentes

#### Diagrama de Clases

## Características de la aplicación
1. Librerias y/o Dependencias más importantes:

   Dependency Name | Usage         | Version | License      |
   -------------   | ------------- | ------- | -------------
   Content Cell    | Content Cell  | Content | Content Cell |
   Content Cell    | Content Cell  | Content | Content Cell |

## Manual de usuario (como ejecutar este programa)
Puede ejecutar el programa en ambiente local o en ambiente de producción.

En ambiente local, tienen dos opciones para ejecutar y/o probar el programa (los siguientes pasos fueron descritos para un entorno en SO Windows 10):
- Correr el proyecto con el siguiente comando:
  - mvn

### 1. A través de los archivos de Jmeter (recomendado)
La creación de los archivos en Jmeter tiene como objetivo simplificar el trabajo de ejecución de los servicios rest,
pensando a futuro poder agregar assets de validación o baterias de pruebas de rendimiento, entre otras cosas, que te brinda un programa como Jmeter.
[Screenshot]

1.1 Descarga Jmeter [aquí](https://curl.se/windows/)
1.2 Abrir el archivo en la ruta firequasar-game/firequasar/resources/performance
1.3 Ejecutar los servicios como disponga (en View Results Tree podrá ver los resultados):
[Screenshot]

### 2. Usando CURL
2.1. Descarga curl [aquí](https://curl.se/windows/)
2.2. Descargar git bash [aquí](https://gitforwindows.org/)
2.3. Estos son 2 ejemplos para la ejecución de algunos de los servicios:
2.3.1.  curl topsecret_split POST service:
<pre><code>curl -X GET "http://localhost:8081/api/topsecret_split/Sato" -H  "accept: application/json" -H "Content-Type: application/json"</code></pre>
2.3.2.  curl topsecret_split GET service:
<pre><code>curl -X GET "http://localhost:8081/api/topsecret_split/Sato" -H  "accept: application/json" -H "Content-Type: application/json"</code></pre>
2.3.3.  Respuesta ejemplo:
[Screenshot]

## Learn More

#### Test Coverage
Informe de Test Coverage ejecutado por JaCoCo el 25/08/2021
[Screenshot]
