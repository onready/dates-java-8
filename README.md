# Manejo de fechas en Java 8 (LocalDate, LocalTime y LocalDateTimes)

Proyecto de ejemplo para ver las distintas maneras de manejar fechas con la clases LocalDate, LocalTime y LocalDateTime de Java 8

### Qué nos pasa con las fechas? ###

El manejo de fechas a veces puede traernos dolores de cabeza a la hora de desarrollar software, más si nos encontramos
en un entorno donde tenemos que comunicarnos con otras aplicaciones de las cuales consumimos información.
Si los equipos que se encargan de cada proyecto deciden arbitrariamente como manejar las fechas seguramente nos encontremos con problemas
a la hora de deserializarlas y viceverza cuando ellos deban hacerlo desde el formato en el que nosotros las serialicemos porque no 
siempre vamos a tener la suerte de que todos elijamos el mismo criterio.
En este proyecto vamos a ver las distintas maneras, de manejar las fechas, que pueden servirles como opción a la hora de concensuar con
otros equipos.

### Proyecto de ejemplo ###

Nuestro ejemplo va a ser una aplicacion Spring Boot con Tomcat Embebido que expone a través de una API REST la siguiente clase de dominio.

{clase de dominio}

#### Por defecto ####

* Si exponemos nuestra entidad en formato JSON sin configurar nada, jackson automaticamente serializará el local date time de la siguiente forma


#### Agregando la dependencia de JSR-310 ####

*  Agregando la siguiente dependencia en maven, Jackson va a comenzar a reconocer los tipos de datos Date y Time como tales.

```xml
<dependency>
  <groupId>com.fasterxml.jackson.datatype</groupId>
  <artifactId>jackson-datatype-jsr310</artifactId>
</dependency>
```
El formato por defecto cambiará, y lo que obtendremos al serializar la fecha será de la siguiente forma:

#### Agregando una propertie, llegamos al standar ####

* Agregando la siguiente propertie:

```java
spring.jackson.serialization.WRITE_DATES_AS_TIMESTAMPS = false
```
Las fechas se serializarán según el standar ISO-8601 quedando de la siguiente forma:



