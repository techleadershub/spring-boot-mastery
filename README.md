# Spring Boot AOP Masterclass

This project demonstrates Aspect-Oriented Programming (AOP) in Spring Boot applications. It showcases various AOP concepts including:

* Logging aspects
* Execution time measurement
* Annotation-based method logging
* Transaction management

## Project Structure

```
spring-boot-aop-masterclass/
├── src/main/java/com/example/aop/
│   ├── annotation/            # Custom annotations for AOP
│   │   ├── LogExecutionTime.java
│   │   └── LogMethod.java
│   ├── aspect/                # AOP aspects
│   │   ├── ExecutionTimeAspect.java
│   │   ├── LoggingAspect.java
│   │   └── MethodLoggingAspect.java
│   ├── config/                # Application configuration
│   │   └── DataInitializer.java
│   ├── controller/            # REST controllers
│   │   └── UserController.java
│   ├── model/                 # Entity models
│   │   └── User.java
│   ├── repository/            # Data repositories
│   │   └── UserRepository.java
│   ├── service/               # Business services
│   │   └── UserService.java
│   └── SpringBootAopMasterclassApplication.java
└── src/main/resources/
    └── application.properties
```

## AOP Features

### 1. Logging Aspect

The `LoggingAspect` provides comprehensive logging for all Spring components:
- Logs method entry with parameters
- Logs method exit with return values
- Logs exceptions when they occur

### 2. Execution Time Aspect

The `ExecutionTimeAspect` measures and logs execution time:
- For methods annotated with `@LogExecutionTime`
- For all service methods automatically

### 3. Method Logging Aspect

The `MethodLoggingAspect` provides detailed method logging through the `@LogMethod` annotation:
- Custom messages can be specified in the annotation
- Logs method entry, parameters, return value, and execution time

## API Endpoints

The application provides the following REST API endpoints for user management:

| Method | URL                         | Description               |
|--------|-----------------------------|-----------------------------|
| GET    | /api/users                  | Get all users               |
| GET    | /api/users/{id}             | Get user by ID              |
| GET    | /api/users/active           | Get active users            |
| GET    | /api/users/search?name=xxx  | Search users by name        |
| POST   | /api/users                  | Create a new user           |
| PUT    | /api/users/{id}             | Update a user               |
| DELETE | /api/users/{id}             | Delete a user               |
| PATCH  | /api/users/{id}/status      | Update user active status   |

## Running the Application

1. Make sure you have Java 17 (or newer) and Maven installed
2. Clone this repository
3. Navigate to the project directory
4. Run the application:
   ```
   mvn spring-boot:run
   ```
5. The application will be available at http://localhost:8080
6. Access the H2 database console at http://localhost:8080/h2-console
   - JDBC URL: `jdbc:h2:mem:aopdb`
   - Username: `sa`
   - Password: `password`

## Testing the API

You can use tools like cURL, Postman, or your web browser to test the API endpoints.

Example cURL commands:

```bash
# Get all users
curl -X GET http://localhost:8080/api/users

# Create a new user
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Alice","lastName":"Johnson","email":"alice.johnson@example.com","phone":"555-666-7777","address":"987 Cedar Ln, Somewhere, USA","active":true}'

# Get user by ID
curl -X GET http://localhost:8080/api/users/1

# Update user status
curl -X PATCH http://localhost:8080/api/users/1/status?active=false
```
