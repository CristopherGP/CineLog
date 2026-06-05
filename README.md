# CineLog
Sistema de Gestion de Peliculas desarrollado en Java y MySql

# CINELOG

Sistema de gestión de películas desarrollado en Java Swing y MySQL.

## Integrantes

- Cristopher Guerrero Puente
- Ana Paulina Lopez Valenzuela
- Omar Grimaldo Barbosa
- Johana Elizabeth Torres Garcia
- Diego Francisco Felix Garza

## Tecnologías Utilizadas

- Java
- Java Swing
- MySQL
- JDBC
- NetBeans
- iText PDF

## Instalación

### 1. Clonar o descargar el proyecto

Descargar el repositorio desde GitHub y descomprimirlo.

### 2. Crear la base de datos

Abrir MySQL Workbench o phpMyAdmin e importar el archivo:

```
Cinelog.sql
```

Esto creará la base de datos, tablas, vistas y registros necesarios.

### 3. Configurar la conexión

Abrir el archivo:

```
src/conexion/Conexion.java
```

Verificar que los datos coincidan con la instalación local de MySQL.

Ejemplo:

```java
String url = "jdbc:mysql://localhost:3306/cinelog";
String usuario = "root";
String password = "Samara182";
```

### 4. Abrir el proyecto

Abrir Apache NetBeans.

Seleccionar:

```
File -> Open Project
```

y abrir la carpeta del proyecto **CineLog**.

### 5. Agregar librerías

Verificar que estén agregadas las siguientes librerías:

- MySQL Connector/J
- iText PDF

### 6. Ejecutar el proyecto

Ejecutar el proyecto desde NetBeans mediante:

```
Run Project (F6)
```

## Funcionalidades

- Inicio de sesión con control de intentos.
- Gestión de usuarios.
- Gestión de películas.
- Gestión de géneros.
- Gestión de directores.
- Gestión de reseñas.
- Reportes PDF.
- Vistas SQL.
- Manejo de imágenes de películas y directores.

## Base de Datos

El proyecto utiliza MySQL y contiene:

- Tablas
  - usuarios
  - peliculas
  - generos
  - directores
  - resenas

- Vistas
  - vista_peliculas
  - vista_peliculas_directores
  - vista_peliculas_generos
  - vista_generos
  - vista_directores
  - vista_resenas
    
## Usuarios para probar Roles

1. Usuario: Admin   Rol: Admin     Password: 123
2. Usuario: Cristo  Rol: Operador  Password: 12345
3. Usuario Johana   Rol: Consultor Password: 1234
 
## Video Explicativo

https://youtu.be/s8hnbfe8goM?feature=shared

## Autor

Proyecto académico desarrollado para la asignatura Programación II.
