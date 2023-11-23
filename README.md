# Spring Boot H2 Database CRUD example by Object User

- Building Rest API with Spring Data JPA
- Way to create Spring Rest Controller to process HTTP requests
- Way to use Spring Data JPA to interact with H2 Database

# H2 Config

- Url Console H2 http://localhost:8080/h2-ui/
- Config param (JDBC URL =jdbc:h2:mem:/test) in Console H2

# How test

1._ Genereta Token in https://jwt.io/

     with payload :  "fechaToken": "2023-04-02",  date is a example
	 
	 the Api Validate Token with Date Now
	 
2._ Import Collection Postman , in your Postman local By test This API
	 
	apiDesafioOnlyBack\backTest\spring-boot-CRUD\src\main\resources	 
	
3._ EndPoints:
	
	CreatedUser -> creation New User
	
	getUsers -> Get All Users save in BD
	
	UpdatedUserByName -> Updated  User exist in BD
	
	DeleteAll -> Delete all Rows in BD
	 


