# Java 8 dates management

Demo project to show different ways of serializing dates with Jackson 

### ¿What happens with dates? ###

Sometimes date managements can be a headache at the time of software development, even more if we are working on a
environment where we communicate with another applications of which we get information.
If every project team decide differently how to manage dates, probably we are going to find troubles at the time of 
serializing or deserializing them, because they have to do it with the format that we used when we expose them. In this 
project we are going to see the different options we have so teams can be agree with one of them.

### Example project ###

Our example is going to be a Spring Boot application with an embedded Tomcat, which exposes a Bus class via an API REST,
where the arrival time is on LocalDateTime format. Adding Spring Boot as pom.xml parent we are bringing to our project 
Jackson as dependency. Regarding to the project branch we are, we can see how the date we return can change.

```java
public class Bus {
    
    private int line;
    private LocalDateTime arrivalTime;

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
```

#### Branch master ####

By default, if we don't configure anything, Jacksone automatically will serialize LocalDateTime as follows:
```json
{
  "line": 37,
  "arrivalTime": {
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

#### Branch JSR-310 ####

When we add the following dependency, Jackson is going to start recognizing Java 8 data date types as they are.

```xml
<dependency>
  <groupId>com.fasterxml.jackson.datatype</groupId>
  <artifactId>jackson-datatype-jsr310</artifactId>
</dependency>
```
Format will change by default, the returned object by the API is going to be this:

```json
{
  "line": 37,
  "arrivalTime": [
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

#### Branch ISO-8601 ####

Finally, if we add the following property to the previous code:

```java
spring.jackson.serialization.WRITE_DATES_AS_TIMESTAMPS = false
```

Dates are going to be serialize according to the standard **ISO-8601**, leaving the response like this:

```json
{
  "line": 37,
  "arrivalTime": "2016-12-29T13:37:19.839"
}
```

#### Other utilities ####

* Serialize formats on-demand adding the following annotation on the attributes:
```java
@JsonFormat(pattern = "yyyy-MM-dd")
```
* If we want hibernate to automatically map this data type, we have to add this dependency:
```xml
<dependency>
	<groupId>org.hibernate</groupId>
	<artifactId>hibernate-java8</artifactId>
</dependency>
```

### Conclusion ###

The way we think is better and we suggest to use is the last one, in the first place because is easier to read from the
frontend. And in the second place, there are a lot of libraries that also use it, which is good for us because it make
easier the interaction with the first ones and the second ones.