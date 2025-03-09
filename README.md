# Spring Boot MVC Masterclass

This project is designed to help students learn Spring MVC architecture, request handling, form validation, session management, and file uploads with hands-on exercises.

## Project Overview

This Spring Boot MVC application demonstrates:
- MVC Architecture with Controller, Service, and Repository layers
- Thymeleaf templating for view rendering
- Form handling and validation
- Session management for user login/logout
- File upload processing
- Bootstrap for responsive UI design

## Project Structure

```
spring-boot-mvc-masterclass/
├── src/main/java/com/example/mvc/
│   ├── controller/            # MVC Controllers
│   │   ├── AuthController.java
│   │   ├── FileUploadController.java
│   │   ├── HomeController.java
│   │   └── UserController.java
│   ├── model/                 # Entity models
│   │   └── User.java
│   ├── repository/            # Data repositories
│   │   └── UserRepository.java
│   ├── service/               # Business services
│   │   └── UserService.java
│   ├── config/                # Application configuration
│   │   └── DataInitializer.java
│   └── SpringBootMvcMasterclassApplication.java
└── src/main/resources/
    ├── static/                # Static resources (CSS, JS)
    ├── templates/             # Thymeleaf templates
    │   ├── auth/              # Authentication templates
    │   │   └── login.html
    │   ├── files/             # File upload templates
    │   │   └── upload.html
    │   ├── layout/            # Shared layout templates
    │   │   └── main.html
    │   ├── users/             # User management templates
    │   │   ├── form.html
    │   │   └── list.html
    │   └── index.html         # Home page
    └── application.properties # Application configuration
```

## Features Implemented

1. **User Management**
   - Create, read, update, and delete user records
   - Form validation with error messages
   - User list with actions

2. **File Upload**
   - File upload handling
   - File storage on server
   - Display uploaded images (for profile pictures)

3. **Session Management**
   - Simple login/logout functionality
   - Session-based authentication
   - Conditional navigation elements based on login status

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
   - JDBC URL: `jdbc:h2:mem:mvcdb`
   - Username: `sa`
   - Password: (leave empty)

## Learning Exercises

Try these exercises to enhance your understanding of Spring MVC:

### Exercise 1: Add Phone Number Field
Add a phone number field to the User entity and update the related components:
1. Add a phone field to the User class with proper validation (@Pattern for phone format)
2. Update the user form template to include the new field
3. Display the phone number in the user list

### Exercise 2: Implement User Search
Create a search feature for the user list:
1. Add a search method in UserRepository using @Query
2. Create a search method in UserService
3. Add a search form in the user list template
4. Implement a search endpoint in UserController

### Exercise 3: Improve File Upload Validation
Enhance the file upload feature with more robust validation:
1. Add file type validation (only accept images)
2. Implement file size validation with custom error messages
3. Add a preview of the selected image before upload

### Exercise 4: Create a Custom Error Page
Implement custom error handling:
1. Create an error.html template
2. Implement a CustomErrorController
3. Style the error page to match the application theme

### Exercise 5: Add Data Export Feature
Implement functionality to export user data:
1. Add an endpoint to export users as CSV
2. Create a service method to generate CSV data
3. Add a download button to the user list page

## Additional Resources
- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Spring MVC Documentation](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html)
- [Thymeleaf Documentation](https://www.thymeleaf.org/documentation.html)
