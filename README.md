
#   Proyecto 1.1-Programación 3

Este es un mini-proyecto para aprender y reforzar los conocimientos en spring-boot; este proyecto esta compuesto principalmente por tres controladores: uno para asignaturas, otro para lugares y otro para grupos; estos controladores son los que contienen los end-points necesarios para manipular los datos y el funcionamiento del proyecto.


## Herramientas utilizadas

![](https://img.shields.io/badge/Vscode-007ACC?style=for-the-badge&logo=visualstudiocode&logoColor=white)

![](https://img.shields.io/badge/Postman-gray?style=for-the-badge&logo=postman&logoColor=orange)

### Lenguaje y Frameworks

![](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)

![](https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=Spring&logoColor=white)


## Documentation

La documentación del proyecto fue hecha con la ayuda de **swagger** por lo tanto esta se puede ver al correr el proyecto y escribir en el navegador:

```
http://localhost:9081/doc/swagger-ui/index.html
```

[Documentation](http://localhost:9081/doc/swagger-ui/index.html)

## API Reference

### El proyecto usa el puerto 9081

- ### CONTROLLER-SUBJECTS

```http
  http://localhost:9081/subjects
``` 
#### Listar todas las asignaturas

```http
  GET /subjects/list
```

#### Agregar una asignatura

```http
  POST /subjects/addSubject
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `SubjectDto` | `SubjectDto` | `@RequestBody` **Requerid** |

Retorna la asignatura que fue agregada.

#### Eliminar asignatura

```http
  DELETE /subjects/deleteSubject/{code}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `code`      | `String` | **Required**. Código de la asignatura que será eliminada |

Retorna la asignatura que fue eliminada.

#### Modificar asignatura

```http
  PUT /subjects/setSubject/{code}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `code`      | `String` | **Required**. Código de la asignatura que será modificada |
| `subjectDto` | `SubjectDto` | `@RequestBody`  **Required** Asignatura que remplazara a la anterior, se debe enviar como Json |

Retorna la asignatura que fue modificada, mejor dicho la que había antes.

#### Listar asignaturas que tienen asignado el mismo lugar

```http
  GET /subjects/samePlace/{identificationPlace}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `identificationPlace`      | `String` | **Required**. Identificación del lugar |

#### Listar asignaturas con en más de un grupo

```http
  GET /subjects/moreOneGroup
```

Retorna las asignaturas que tienen o están en más de un grupo.

#### Listar asignaturas que tienen un mismo horario

```http
  GET /subjects/sameSchedule
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `schedule`      | `String[]` | `@RequestBody` **Required**. Horario que debe tener las asignaturas que serán listadas |

- ### CONTROLLER-PLACES

```http
  http://localhost:9081/places
``` 
#### Listar todos los lugares

```http
  GET /places/list
```

#### Agregar un lugar

```http
  POST /places/addPlace
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `placeDto` | `PlaceDto` | `@RequestBody` **Requerid** El lugar que será agregado |

Retorna el lugar que fue agregado.

#### Eliminar lugar

```http
  DELETE /places/deletePlace/{identification}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `identification`      | `String` | **Required**. Identificación del lugar que será eliminado |

Retorna el lugar que fue eliminado.

#### Modificar lugar

```http
  PUT /places/setPlace/{identification}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `identification`      | `String` | **Required**. Identificación del lugar que será modificado |
| `place` | `PlaceDto` | `@RequestBody`  **Required** Lugar que remplazara al anterior, se debe enviar como Json |

Retorna el lugar que fue modificado.

- ### Controller-Groups

```http
  http://localhost:9081/groups
``` 
#### Listar todos los grupos

```http
  GET /groups/list
```

#### Agregar un grupo

```http
  POST /groups/addGroup
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `groupDto` | `groupDto` | `@RequestBody` **Requerid** El grupo que será agregado |

Retorna el grupo que fue agregado.

#### Eliminar grupo

```http
  DELETE /groups/deleteGroup/{identificationPlace}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `identificationPlace`      | `String` | **Required**. Identificación del lugar que le pertenece al grupo que será eliminado |
| `schedule`      | `String[]` | **Required**. Horario del grupo que será eliminado |

Retorna el grupo que fue eliminado.

#### Modificar lugar

```http
  PUT /groups/setGroup/{numberGroup}/{subjectCode}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `numberGroup`      | `int` | **Required**. Número del grupo que será modificado |
| `subjectCode` | `String` | **Required** Código de la matería que le pertenece a este grupo |
| `groupDto` | `GroupDto` | `@RequestBody` **Required** Nuevo grupo|

Retorna el grupo que fue modificado, mejor dicho el que exístia anteriormente.
## Authors

- [@fredylopez01](https://github.com/fredylopez01)
