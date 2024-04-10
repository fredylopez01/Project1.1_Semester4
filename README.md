# Título: CollageProject
## **Descripción:**

  Programa de manejo de datos donde solo se dispone del **``ejecutable``** .jar. 
  Este es un mini-proyecto desarrollado principalmete con el objectivo para aprender y reforzar los conocimientos en spring-boot; este proyecto esta compuesto principalmente por tres controladores: uno para asignaturas, otro para lugares y otro para grupos; estos controladores son los que contienen los end-points necesarios para manipular los datos y el funcionamiento del proyecto.

## **Requisitos o prerrequisitos para ejecución:**

  - Jdk 17
  - Puerto Libre 9081
  - Verificar que el puerto este abierto y disponible
  - Windows 11 o superior
  - Linux-Sin Probar

## **Instalación de la Libreria**

1. Abrir la terminal de comandos. 
2. Ahora debera copiar la siguiente linea, remplazando la *ruta* por la ruta donde esta el jar de la libreria en su computador. Todo debe estar en una única linea.

```
mvn install:install-file -Dfile=ruta -DartifactId=list -DgroupId="co.edu.uptc.SimpleUptcList.services" -Dversion="1.0" -Dpackaging=jar
```
Después de esto ya se reconocera la libreria; en el archivo pom.xml la importación de la libreria se debe de ver de la siguiente forma:

```
  <dependency>
			<groupId>co.edu.uptc.SimpleUptcList.services</groupId>
			<artifactId>list</artifactId>
			<version>1.0</version>
	</dependency>
```

## **Instalación**

- Crear una carpeta en el lugar de preferencia
- Copiar el jar en esta ubicación

## **Ejecución**
Dentro del proyecto hay un .jar del proyecto el cual se llama: **```project1.1-V_1.0.0.jar```**, y para poder utilizar este jar o mejor dicho ejecutarlo, sólo se debe abrir la consola de comandos dentro de esta carpeta y escribir: 
```
java –jar project1.1-V_1.0.0.jar
``` 
y el jar empezara a ejecutarse sin ningún problema.

## **Usos de endpoint:**

### ``End point para eliminar los lugares con el mismo nombre o un fragmento de este``

```
DELETE http://localhost:9081/places/deletePlaceByName/{namePlace}
```
- El *namePlace* es el nombre o fragmento del lugar que quiere eliminar
- Retorna la cantidad de lugares que coincidian y fueron eliminaron

### Documentation

La documentación del proyecto fue hecha con la ayuda de **swagger** por lo tanto esta se puede ver al correr el proyecto y escribir en el navegador:

```
http://localhost:9081/doc/swagger-ui/index.html
```

[Documentation](http://localhost:9081/doc/swagger-ui/index.html)

## Herramientas utilizadas

![](https://img.shields.io/badge/Vscode-007ACC?style=for-the-badge&logo=visualstudiocode&logoColor=white)

![](https://img.shields.io/badge/Postman-gray?style=for-the-badge&logo=postman&logoColor=orange)

### Lenguaje y Frameworks

![](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)

![](https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=Spring&logoColor=white)
