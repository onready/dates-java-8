# Manejo de fechas con Dates de Java 8

Proyecto de ejemplo para ver las distintas maneras de manejar fechas con los dates de Java 8

### ¿Qué nos pasa con las fechas? ###

El manejo de fechas a veces puede traernos dolores de cabeza a la hora de desarrollar software, más si nos encontramos
en un entorno donde tenemos que comunicarnos con otras aplicaciones de las cuales consumimos información.
Si los equipos que se encargan de cada proyecto deciden arbitrariamente como manejar las fechas seguramente nos encontremos con problemas
a la hora de deserializarlas y viceverza cuando ellos deban hacerlo desde el formato en el que nosotros las serialicemos porque no 
siempre vamos a tener la suerte de que todos elijamos el mismo criterio.
En este proyecto vamos a ver las distintas maneras, de manejar las fechas, que pueden servirles como opción a la hora de concensuar con
otros equipos.

### Proyecto de ejemplo ###

Nuestro ejemplo va a ser una aplicacion Spring Boot con Tomcat Embebido que expone a través de una API REST la siguiente clase de dominio, de acuerdo al brach en el que estemos podremos ver como va cambiando la fecha que devolvemos.

```java
public class Colectivo {
    
    private int linea;
    private LocalDateTime horaDeArribo;

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public LocalDateTime getHoraDeArribo() {
        return horaDeArribo;
    }

    public void setHoraDeArribo(LocalDateTime horaDeArribo) {
        this.horaDeArribo = horaDeArribo;
    }
}
```

#### Por defecto (branch master)####

Si exponemos nuestra entidad en formato JSON sin configurar nada, jackson automaticamente serializará el LocalDateTime de la siguiente forma

```json
{
  "linea": 37,
  "horaDeArribo": {
    "month": "DECEMBER",
    "year": 2016,
    "dayOfMonth": 29,
    "dayOfWeek": "THURSDAY",
    "dayOfYear": 364,
    "monthValue": 12,
    "hour": 13,
    "minute": 17,
    "nano": 208000000,
    "second": 23,
    "chronology": {
      "id": "ISO",
      "calendarType": "iso8601"
    }
  }
}
```

#### Agregando la dependencia de JSR-310 (branch JSR-310) ####

Agregando la siguiente dependencia en maven, Jackson va a comenzar a reconocer los tipos de datos de las fechas de Java 8 como tales.

```xml
<dependency>
  <groupId>com.fasterxml.jackson.datatype</groupId>
  <artifactId>jackson-datatype-jsr310</artifactId>
</dependency>
```
El formato por defecto cambiará, y el objeto devuelto por la API será el siguiente:

```json
{
  "linea": 37,
  "horaDeArribo": [
    2016,
    12,
    29,
    13,
    33,
    59,
    412000000
  ]
}
```

#### Agregando una property, llegamos al standar (branch ISO-8601) ####

Agregando la siguiente property:

```java
spring.jackson.serialization.WRITE_DATES_AS_TIMESTAMPS = false
```
Las fechas se serializarán según el standar **ISO-8601**, quedando el response de la siguiente manera:

```json
{
  "linea": 37,
  "horaDeArribo": "2016-12-29T13:37:19.839"
}
```

#### Otras utilidades ####

* Serializar los formatos a demanda agregando la siguiente anotación en los atributos:
```java
@JsonFormat(pattern = "yyyy-MM-dd")
```

* Si queremos que hibernate automaticamente nos pueda mapear este tipo de datos tenemos que agregar la siguiente dependencia:
```xml
<dependency>
	<groupId>org.hibernate</groupId>
	<artifactId>hibernate-java8</artifactId>
</dependency>
```


### Conclusión ###
La forma que a nosotros nos parece más adecuada para usar es la última, una ventaja es que nos resulta más facil a la hora de consumirla desde la capa de presentación para mostrarla, ya sea desde Javascript o desde JSP.
Otra ventaja de usar este standar es que muchos otros sistemas también lo utilizan y entiende como usarlo, con lo cual facilita la interacción con estos.
No creemos que haya que hacerlo si o si de determinada manera, siempre va a terminar dependiendo de la necesidad del negocio.
