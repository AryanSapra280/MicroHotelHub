![image](https://github.com/AryanSapra280/microhotel-user-service/assets/167304409/74f46126-2400-43c4-add6-44c0b281a456)

# user-service Microservice

The userService microservice provides functionality for managing user information, including retrieval, creation, updating, and deletion of user records. It is designed as part of a larger system, leveraging Spring Boot and JPA for efficient development and integration.

## Features

* Retrieve all users

* Retrieve user by ID

* Save a new user

* Update user information

* Handle exceptions such as user not found or duplicate email

## Technologies Used

* Java

* Spring Boot

* Spring Data JPA

* Lombok

* Getting Started

## To get started with using the userService microservice, follow these steps:

* Clone the repository to your local machine.

* Build the project using Maven or your preferred build tool.

* Configure the database settings in the application.properties file:

## Properties
```
spring.application.name=UserService
spring.datasource.url=jdbc:mysql://localhost:3306/microservices
spring.datasource.username=root
spring.datasource.password=Admin@123
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update
```
**Ensure you have Java Development Kit (JDK) installed on your system.**

## Run the application using the UserServiceApplication class.

**API Endpoints**
```
GET /users/all: Retrieve all users.
GET /users/{id}: Retrieve user by ID.
POST /users/save: Save a new user.
PUT /users/update/{email}: Update user information.
```
## Exception Handling

The microservice handles the following exceptions:

* **UserNotFound**: When attempting to retrieve a user that does not exist.
* **DuplicateEmail**: When attempting to save a user with an email that already exists.
