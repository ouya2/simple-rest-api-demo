# Customer CRUD API Service
A simple demo to show the rest api architecture with CRUD operation.

This is a sample Java / Maven / Spring Boot (version 1.5.16) application that can be used as a starter for creating a microservice complete with built-in health check, metrics.

## About the Service

The service is a simple customer service. User can use it to create, update, retrieve and delete the customer. It uses in-memory database (H2) to store the data. 

## How to Run 

This application is packaged as a war which has Tomcat 8 embedded. You run it using the ```mvn clean spring-boot:run``` command.

* Clone this repository 
* Make sure you are using JDK 1.8 and Maven 3.x
* You can build the project and run the tests by running ```mvn clean install```



### Get information about system health, configurations, etc.
```
http://localhost:8081/env
http://localhost:8081/health
http://localhost:8081/info
http://localhost:8081/metrics
```

### Create a customer resource

```
POST /api/customers
Accept: application/json
Content-Type: application/json

{
  "creditRating": 1,
  "firstName": "dick",
  "initials": "DS",
  "lastName": "smith",
  "maritalStatus": "married",
  "middleName": "john",
  "nabCustomer": true,
  "sex": "M",
  "title": "Mr",
  "addresses":[{
      "addressNumber": "14",
      "city": "sydney",
      "country": "new zealand",
      "pinCode": "7654",
      "state": "NSW",
      "street": "Crandale",
      "suburb": "Collins"
    }]
}
```

### To view Swagger 2 API docs

Run the server and browse to http://localhost:8080/swagger-ui.html 

### To view your H2 in-memory datbase

To view and query the database you can browse to http://localhost:8080/h2-console. JDBC URL: jdbc:h2:mem:customercrud with default username is 'sa' with a blank password.